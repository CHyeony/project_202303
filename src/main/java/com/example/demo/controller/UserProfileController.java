package com.example.demo.controller;

import com.example.demo.auth.TokenParser;
import com.example.demo.dto.UserProfileDTO;
import com.example.demo.entity.UserAccount;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;

    private final TokenParser tokenParser;

    // Authentication optional ========
    @GetMapping("/{username}")
    public UserProfileDTO getUserProfile(
            @PathVariable("username") String username,
         @RequestHeader(value = "Authorization", required = false) String token) {

        return userProfileService.getProfileByUsername(username);
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
