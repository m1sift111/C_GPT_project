package com.example.demo1122.DTO;

import java.io.Serializable;

public class TeacherDTO implements Serializable {
    private String teacherID;
    private Boolean identity;
    private String name;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
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
