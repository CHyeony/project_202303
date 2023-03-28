package com.example.demo.auth;

import org.springframework.stereotype.Component;

import com.example.demo.error.BusinessException;
import com.example.demo.error.ErrorCode;

@Component
public class TokenParser {

	public long parseToken(String token) {
		if (token == null) {
			throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);
		}
		String[] splittedToken = token.split(" ");
		if (splittedToken.length != 2) {
			throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);
		}
		String a = splittedToken[0];
		String userIdAsString = splittedToken[1];
		if (!a.equals("Token")) {
			throw new BusinessException(ErrorCode.AUTHENTICATION_ERROR);
		}
		try {
			Long userId = Long.valueOf(userIdAsString);
			return userId;
		} catch (NumberFormatException e) {
			throw new BusinessException(e, ErrorCode.AUTHENTICATION_ERROR);
		}
	}
}
