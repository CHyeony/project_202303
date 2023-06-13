package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.entity.Follow;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.FollowRepository;
import com.example.demo.repository.UserAccountRepository;
import com.example.demo.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.query.criteria.internal.BasicPathUsageException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j //LOG
public class UserProfileService {

    private  final UserAccountRepository userAccountRepository;

    private final FollowRepository followRepository;

    @Transactional(readOnly = true)
    public UserProfileDTO getProfileByUsername(String username) {

       UserAccount userProfile = userAccountRepository.findByUsername(username)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

       return new UserProfileDTO(userProfile.getUsername(), userProfile.getBio(), userProfile.getImage());
    }

    @Transactional(readOnly = true)
    public UserProfileDTO getProfileByUsername(long userId, String username) {
        UserAccount me = userAccountRepository.findById(userId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        UserAccount userProfile = userAccountRepository.findByUsername(username)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

        boolean following = followRepository.findByFromIdAndToId(me.getId(), userProfile.getId())
            .isPresent();

        return UserProfileDTO.builder()
            .username(userProfile.getUsername())
            .bio(userProfile.getBio())
            .image(userProfile.getImage())
            .following(following)
            .build();
    }

    @Transactional
    public UserProfileDTO follow(long fromUserId, String toUsername) {
        UserAccount fromUser = userAccountRepository.findById(fromUserId)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        UserAccount toUser = userAccountRepository.findByUsername(toUsername)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));
        long toUserId = toUser.getId();

        Follow follow = followRepository.findByFromIdAndToId(fromUserId, toUserId)
            .orElse(null);
        if (follow != null) {
            throw new BusinessException(ErrorCode.FOLLOW_ALREADY_EXISTS);
        }

        Follow newFollow = Follow.builder()
            .from(fromUser)
            .to(toUser)
            .build();
        followRepository.save(newFollow);

        return UserProfileDTO.builder()
            .username(toUser.getUsername())
            .bio(toUser.getBio())
            .image(toUser.getImage())
            .following(true)
            .build();
    }
}
