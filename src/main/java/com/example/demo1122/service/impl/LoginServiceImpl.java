package com.example.demo1122.service.impl;

import com.example.demo1122.domain.LoginUser;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.domain.User;
import com.example.demo1122.service.LoginService;
import com.example.demo1122.utils.JwtUtil;
import com.example.demo1122.utils.RedisCache;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;



@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    @Override
    public ResponseResult login(User user) {
        //用户认证
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getUsername(),user.getPassword());
        Authentication authenticate;
        try {
            authenticate = authenticationManager.authenticate(authenticationToken);
        } catch (AuthenticationException e) {
            // 认证失败的逻辑
            throw new RuntimeException("用户名或密码错误", e);
        }

        if (!authenticate.isAuthenticated()) {
            // 认证失败的逻辑
            throw new RuntimeException("用户名或密码错误");
        }

// 认证通过的逻辑
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userid = loginUser.getUser().getId().toString();
        String jwt = JwtUtil.createJWT(userid);
        Map<String,String> map = new HashMap<>();
        map.put("token", jwt);
        redisCache.setCacheObject("login:" + userid, loginUser);
        return new ResponseResult(200, "登录成功", map);
    }
}

