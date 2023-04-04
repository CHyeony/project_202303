package com.example.demo.auth;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;

public class TokenParserTest {

	private TokenParser tokenParser = new TokenParser();

	@Test
	@DisplayName("토큰이 null 일 때")
	public void testTokenIsNull() {
		// given
		String token = null;

		// when
		BusinessException be = null;
		try {
			long userId = tokenParser.parseToken(token);
		} catch (BusinessException e) {
			be = e;
		}

		// then
		Assertions.assertThat(be).isNotNull();
		Assertions.assertThat(be.getErrorCode()).isEqualTo(ErrorCode.AUTHENTICATION_ERROR);
	}

	@Test
	@DisplayName("userId 가 long 타입이 아닐 때")
	public void testNumberFormatException() {
		// given
		String token = "Token C";

		// when
		BusinessException be = null;
		try {
			tokenParser.parseToken(token);
		} catch (BusinessException e) {
			be = e;
		}

		// then
		Assertions.assertThat(be).isNotNull();
		Assertions.assertThat(be.getErrorCode()).isEqualTo(ErrorCode.AUTHENTICATION_ERROR);
		Assertions.assertThat(be.getCause()).isInstanceOf(NumberFormatException.class);
	}

	@Test
	@DisplayName("token 값 앞에 Token 이라는 문자열이 와야 함")
	public void testToken() {
		// given
		String token = "token ab";

		// when
		BusinessException be = null;
		try {
			tokenParser.parseToken(token);
		} catch (BusinessException e) {
			be = e;
		}

		// then
		Assertions.assertThat(be).isNotNull();
		Assertions.assertThat(be.getErrorCode()).isEqualTo(ErrorCode.AUTHENTICATION_ERROR);
	}

	@Test
	@DisplayName("정상적인 토큰 검증")
	public void testParseToken() {
		// given
		String token = "Token 123";

		// when
		long result = tokenParser.parseToken(token);

		// then
		Assertions.assertThat(result).isEqualTo(123);
	}
}
