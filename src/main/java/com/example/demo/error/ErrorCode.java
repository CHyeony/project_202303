package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

	EMAIL_DUPLICATED_ERROR("00001", HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
	UNKNOWN_SERVER_ERROR("00002", HttpStatus.BAD_REQUEST, "알 수 없는 서버 에러입니다.");

	private final String code;
	private final HttpStatus status;
	private final String description;
}
