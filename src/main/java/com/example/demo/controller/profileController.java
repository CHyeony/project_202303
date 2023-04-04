package com.example.demo.controller;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegistrationRequest;
import com.example.demo.dto.UserDto;
import com.example.demo.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/profiles")
@RequiredArgsConstructor
public class profileController {
@Autowired
    private final UserService userService;

    @PostMapping("/{userId}")
    public Map<String, Object> updateUser(
            @PathVariable("userId") Long userId,
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            UserDto userDTO
            ) {

        UserDto user = userService.updateUser(userId,username,email,userDTO);
        Map<String, Object> responseBody = new HashMap<>();
        responseBody.put("user", user);
        return responseBody;
    }




}
