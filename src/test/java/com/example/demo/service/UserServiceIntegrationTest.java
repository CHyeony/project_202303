package com.example.demo.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.UserAccount;
import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;
import com.example.demo.repository.UserAccountRepository;

@SpringBootTest
@ActiveProfiles("local")
public class UserServiceIntegrationTest {

	@Autowired
	private UserService userService;

	@Autowired
	private UserAccountRepository userAccountRepository;

	@Test
	public void testRegister() {
		UserAccount emailAccount = UserAccount.builder()
			.email("abcd@naver.com")
			.build();
		userAccountRepository.save(emailAccount);

		UserDto userDto = UserDto.builder()
			.email("abcd@naver.com")
			.password("123123")
			.username("abcd")
			.build();

		try {
			userService.register(userDto);
			Assertions.fail("예외가 발생해야 합니다.");
		} catch (BusinessException e) {
			Assertions.assertThat(e.getErrorCode())
				.isEqualTo(ErrorCode.EMAIL_DUPLICATED_ERROR);
		}
	}
}
