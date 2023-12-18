package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * <p>
 * 
 * </p>
 *
 * @author baomidou
 * @since 2023-12-11
 */
@ApiModel(value = "Class对象", description = "")
public class Class implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "classID", type = IdType.AUTO)
    private Integer classID;

    private String className;

    private String teacherID;

    private Integer courseID;

    public Integer getClassID() {
        return classID;
    }

    public void setClassID(Integer classID) {
        this.classID = classID;
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
    public String getTeacherID() {
        return teacherID;
    }

    public void setTeacherID(String teacherID) {
        this.teacherID = teacherID;
    }
    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }

    @Override
    public String toString() {
        return "Class{" +
            "classID=" + classID +
            ", className=" + className +
            ", teacherID=" + teacherID +
            ", courseID=" + courseID +
        "}";
    }
}
