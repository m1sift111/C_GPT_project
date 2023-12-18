package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-12-08
 */
@ApiModel(value = "Teacher对象", description = "")
public class Teacher implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    @TableField(value = "teacherID")
    private String teacherID;
    @TableField(value = "password")
    private String password;
    @TableField(value = "identity")
    private Boolean identity;
    @TableField(value = "name")
    private String name;

    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
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
        return "Teacher{" +
            "teacherID=" + teacherID +
            ", password=" + password +
            ", identity=" + identity +
            ", name=" + name +
        "}";
    }
}
