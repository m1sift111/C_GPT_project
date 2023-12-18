package com.example.demo1122.controller;

import com.example.demo1122.domain.RegistrationRequest;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.domain.User;
import com.example.demo1122.service.LoginService;
import com.example.demo1122.service.UsersignupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private UsersignupService userService;

    @PostMapping("/user/login")
    public ResponseResult login(@RequestBody User user){
        return loginService.login(user);
    }


    @PostMapping("/")
    public ResponseResult registerUser(@RequestBody RegistrationRequest request) {
        return userService.registerUser(request.getUsername(), request.getPassword(),request.getEmail(),request.getPhonenumber());
    }
}


