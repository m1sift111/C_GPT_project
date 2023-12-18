package com.example.demo1122.service;

import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.domain.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static org.apache.logging.log4j.util.Strings.isEmpty;

@Service
public class UsersignupService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 正确的构造函数
    public UsersignupService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    @Transactional  // 添加这个注解
    public ResponseResult registerUser(String username, String password,String email, String phonenumber) {
        if (!isEmpty(username)){
            if (!isEmpty(password)) {
                // 检查用户是否已存在
                if (userRepository.findByUsername(username) == null) {
                    // 创建用户并加密密码
                    User user = new User();
                    user.setUsername(username);
                    user.setEmail(email);
                    user.setPhonenumber(phonenumber);
                    user.setPassword(passwordEncoder.encode(password));
                    userRepository.save(user);

                    System.out.println("成功注册");
                    return new ResponseResult(205, "注册成功");
                } else {
                    System.out.println("shibai");
                    System.out.println(username);
                    return new ResponseResult(203,"用户名已存在");
                }
            } else{
                return new ResponseResult(204,"密码为空");
            }
        }
        else {
            System.out.println("用户名为空");
            return new ResponseResult(202,"用户名为空");
        }

    }

}
