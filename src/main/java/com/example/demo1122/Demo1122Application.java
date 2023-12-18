package com.example.demo1122;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@MapperScan("com.example.demo1122.mapper")
@EnableJpaRepositories(basePackages = "com.example.demo1122.service")
@EnableWebMvc
@EnableTransactionManagement
public class Demo1122Application {
    public static void main(String[] args){
        SpringApplication.run(Demo1122Application.class,args);
    }

}
