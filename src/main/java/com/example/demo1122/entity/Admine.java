package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;

import java.io.Serializable;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-12-06
 */
@ApiModel(value = "Admine对象", description = "")
public class Admine implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    private String admineID;

    private String password;

    private Boolean identity;

    private String name;

    public String getAdmineID() {
        return admineID;
    }

    public void setAdmineID(String admineID) {
        this.admineID = admineID;
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
        return "Admine{" +
            "admineID=" + admineID +
            ", password=" + password +
            ", identity=" + identity +
            ", name=" + name +
        "}";
    }
}
