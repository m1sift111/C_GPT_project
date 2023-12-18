package com.example.demo1122.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomAuthenticationFailureHandler implements AuthenticationFailureHandler {

    private final ObjectMapper objectMapper;

    public CustomAuthenticationFailureHandler(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
       public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        // 设置响应状态为 401 Unauthorized
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 创建错误响应信息
        Map<String, Object> errorResponse = new HashMap<>();
        errorResponse.put("status", HttpServletResponse.SC_UNAUTHORIZED);
        errorResponse.put("error", "Unauthorized");
        errorResponse.put("message", "用户名或密码错误");

        // 将错误信息转换为 JSON 格式并写入响应
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        objectMapper.writeValue(response.getWriter(), errorResponse);
    }

}
