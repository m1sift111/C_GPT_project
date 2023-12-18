package com.example.demo1122.domain;

public class RegistrationRequest {

    private String username;
    private String password;
    private String email;
    private String phonenumber;
    // 必须提供默认构造函数，以便框架能够实例化该类
    public RegistrationRequest() {
    }

    public RegistrationRequest(String username, String password, String email, String phonenumber) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.phonenumber = phonenumber;
    }

    // getters and setters

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public String getPhonenumber(){
        return phonenumber;
    }


}
