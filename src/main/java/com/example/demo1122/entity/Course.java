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
 * @since 2023-12-09
 */
@ApiModel(value = "Course对象", description = "")
public class Course implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    @TableField(value = "courseID")
    private Integer courseID;

    @TableField(value = "courseName")
    private String courseName;

    public Integer getCourseID() {
        return courseID;
    }

    public void setCourseID(Integer courseID) {
        this.courseID = courseID;
    }
    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    @Override
    public String toString() {
        return "Course{" +
            "courseID=" + courseID +
            ", courseName=" + courseName +
        "}";
    }
}
