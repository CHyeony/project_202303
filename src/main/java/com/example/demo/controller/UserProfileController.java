package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.service.UserProfileService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class UserProfileController {

	private final UserProfileService userProfileService;

	private final TokenParser tokenParser;

	// Authentication optional ========
	@GetMapping("/{username}")
	public UserProfileDTO.ProfileResponse getUserProfile(
		@PathVariable("username") String username,
		@RequestHeader(value = "Authorization", required = false) String token) {

		if (token == null) {
			UserProfileDTO userProfileDTO = userProfileService.getProfileByUsername(username);
            return new UserProfileDTO.ProfileResponse(userProfileDTO);
		} else {
			long userId = tokenParser.parseToken(token);
			UserProfileDTO userProfileDTO = userProfileService.getProfileByUsername(userId, username);
            return new UserProfileDTO.ProfileResponse(userProfileDTO);
		}
	}

	@PostMapping("/{username}/follow")
	public UserProfileDTO.ProfileResponse follow(
		@PathVariable("username") String username,
		@RequestHeader(value = "Authorization") String token
	) {
		long userId = tokenParser.parseToken(token);
		UserProfileDTO userProfileDTO = userProfileService.follow(userId, username);
		return new UserProfileDTO.ProfileResponse(userProfileDTO);
	}
}
