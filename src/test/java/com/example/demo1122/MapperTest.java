package com.example.demo1122;

import com.example.demo1122.domain.User;
import com.example.demo1122.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@SpringBootTest
public class MapperTest {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void TestBCryptPasswordEncoder(){
        BCryptPasswordEncoder demo1 = new BCryptPasswordEncoder();
        System.out.println(demo1.encode("zty4239155"));
    }



    @Test
    public void testUserMapper(){
        List<User> users = userMapper.selectList(null);
        System.out.println(users);
    }
}
