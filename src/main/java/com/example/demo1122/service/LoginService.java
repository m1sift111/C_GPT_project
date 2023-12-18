package com.example.demo1122.service;

import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.domain.User;

public interface LoginService {

    ResponseResult login(User user);
}
