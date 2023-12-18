package com.example.demo1122.DTO;

import java.io.Serializable;

// StudentDTO 类
public class StudentDTO implements Serializable {
    private String studentID;
    private Boolean identity;
    private String name;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public Boolean getIdentity() {
        return identity;
    }

    public void setIdentity(Boolean identity) {
        this.identity = identity;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
