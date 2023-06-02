package com.example.demo.service;


import com.example.demo.dto.UserDto;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
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
    @Transactional(readOnly = true)
    public UserProfileDTO getProfileByUsername(String username) {

       UserAccount userProfile = userAccountRepository.findByUsername(username)
            .orElseThrow(() -> new BusinessException(ErrorCode.USER_NOT_FOUND));

       return new UserProfileDTO(userProfile.getUsername(), userProfile.getBio(), userProfile.getImage());
    }
}
