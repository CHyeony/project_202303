package com.example.demo.controller;

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

    // Authentication optional ========
    @GetMapping("/{username}")
    public UserProfileDTO getUserProfile(
            @PathVariable("username") String username,
         @RequestHeader(value = "Authorization", required = false) String token) {
       // UserAccount profile = userProfileService.getProfileByUsername(username);
        //return ResponseEntity.ok(profile);
        return userProfileService.getProfileByUsername(username);
    }
}
