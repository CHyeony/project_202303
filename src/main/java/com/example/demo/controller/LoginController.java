package com.example.demo.controller;

import com.example.demo.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@RestController
@RequestMapping("/api/users/login")
@RequiredArgsConstructor
public class LoginController {

private LoginService loginService;


}
