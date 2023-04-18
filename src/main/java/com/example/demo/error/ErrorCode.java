package com.example.demo.error;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {

	EMAIL_DUPLICATED_ERROR("00001", HttpStatus.BAD_REQUEST, "이미 존재하는 이메일입니다."),
	UNKNOWN_SERVER_ERROR("00002", HttpStatus.BAD_REQUEST, "알 수 없는 서버 에러입니다."),
	LOGIN_FAILED_ERROR("00003", HttpStatus.UNAUTHORIZED, "로그인에 실패했습니다."),
	AUTHENTICATION_ERROR("00004", HttpStatus.FORBIDDEN, "잘못된 인증입니다."),
	USER_NOT_FOUND("00005", HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
	ARTICLE_NOT_FOUND("00006", HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
	ARTICLE_NOT_MINE("00007", HttpStatus.FORBIDDEN, "본인이 작성한 게시글만 수정할 수 있습니다.");

	private final String code;
	private final HttpStatus status;
	private final String description;
}
