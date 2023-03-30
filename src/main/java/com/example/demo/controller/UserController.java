package com.example.demo.controller;

import java.util.HashMap;
import java.util.Map;

import com.example.demo.auth.TokenParser;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.RegistrationRequest;
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
	public Map<String, Object> getMe(@RequestHeader("Authorization") String token){
		long userId= tokenParser.parseToken(token);
		UserDto userResponse = userService.getUser(userId);
		Map<String, Object> responseBody = new HashMap<>();
		responseBody.put("user", userResponse);

		return responseBody;
	}
}
