package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UsersController {

	private final UserService userService;

	@PostMapping
	public Map<String, Object> register(@RequestBody RegistrationRequest request) {
		UserDto userResponse = userService.register(request.getUser());
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("user", userResponse);
		return responseBody;
	}

	@PostMapping("/login")
	public Map<String, Object> login(@RequestBody LoginRequest loginRequest) {
		UserDto user = userService.login(loginRequest);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("user", user);
		return responseBody;
	}
}
