package com.example.demo.controller;

import com.example.demo.dto.UserProfileDTO;
import com.example.demo.service.UserProfileService;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
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
    public Map<String, Object> getUserProfile(
       @RequestHeader(value = "Authorization", required = false) String token
       ,@PathVariable String username
    ){
        Map<String, Object> responseEntity = new HashMap<>();
        UserProfileDTO userProfileDTO = userProfileService.getUserProfile(username);
        responseEntity.put("UserProfile",userProfileDTO);
        return responseEntity;
    }
}
