package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-12-07
 */
@Data
@TableName(value = "student")
public class Student implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    @TableField(value = "studentID")
    private String studentID;
    @TableField(value = "password")
    private String password;
    @TableField(value = "identity")
    private Boolean identity;
    @TableField(value = "name")
    private String name;

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    @Override
    public String toString() {
        return "Student{" +
            "studentID=" + studentID +
            ", password=" + password +
            ", identity=" + identity +
            ", name=" + name +
        "}";
    }
}
