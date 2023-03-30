package com.example.demo.service;

import com.example.demo.dto.LoginRequest;
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

	// LOGIN//////////////////////////////////////////////////////
	@Transactional(readOnly = true)
	public UserDto login(LoginRequest loginRequest){
		String email = loginRequest.getUser().getEmail();
		String password = loginRequest.getUser().getPassword();

		// EMAIL 있는지
		UserAccount user = userAccountRepository.findByEmail(email)
				.orElseThrow(()->new BusinessException(ErrorCode.LOGIN_FAILED_ERROR));
		// PASSWORD 맞는지
		if(!user.getPassword().equals(password)) throw new BusinessException(ErrorCode.LOGIN_FAILED_ERROR);

		return UserDto.toUserDto(user);
	}

	@Transactional(readOnly = true)
	public UserDto getUser(long userId){
		UserAccount user = userAccountRepository.findById(userId)
				.orElseThrow(()->new BusinessException(ErrorCode.USER_NOT_FOUND));
		return UserDto.toUserDto(user);
	}
}
