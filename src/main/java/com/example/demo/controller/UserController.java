package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.UpdateUserRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	private final TokenParser tokenParser;

	@GetMapping
	public Map<String, Object> getMe(@RequestHeader("Authorization") String token) {
		long userId = tokenParser.parseToken(token);
		UserDto userResponse = userService.getUser(userId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("user", userResponse);
		return responseBody;
	}

	@PutMapping
	public Map<String, Object> updateUser(
		@RequestHeader("Authorization") String token,
		@RequestBody UpdateUserRequest request
	) {
		long userId = tokenParser.parseToken(token);
		UserDto userDto = userService.update(userId, request.getUser());
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("user", userDto);
		return responseBody;
	}
}
