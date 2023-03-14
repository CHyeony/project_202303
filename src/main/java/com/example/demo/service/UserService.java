package com.example.demo.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserAccountRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

	private final UserAccountRepository userAccountRepository;

	@Transactional
	public UserDto register(UserDto userDto) {
		UserAccount emailAccount = userAccountRepository.findByEmail(userDto.getEmail())
			.orElse(null);
		if (emailAccount != null) {
			throw new BusinessException(ErrorCode.EMAIL_DUPLICATED_ERROR);
		}

		UserAccount userAccount = UserAccount.builder()
			.username(userDto.getUsername())
			.email(userDto.getEmail())
			.password(userDto.getPassword())
			.build();
		userAccountRepository.save(userAccount);

		return UserDto.toUserDto(userAccount);
	}
}
