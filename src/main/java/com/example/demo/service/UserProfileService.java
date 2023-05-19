package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserProfileService {

    private UserProfileRepository userProfileRepository;

    @Transactional(readOnly = true)
    public UserProfileDTO getUserProfile(String username){
        UserAccount userProfile = userProfileRepository.findByUserName(username)
                .orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));

        return UserProfileDTO.toProfileDto(userProfile);
    }

}
