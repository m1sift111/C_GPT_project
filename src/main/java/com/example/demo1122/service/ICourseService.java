package com.example.demo1122.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Course;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-09
 */
public interface ICourseService extends IService<Course> {
    public ResponseResult saveCourse(Course course);
    public ResponseResult saveCourseList(List<Course> courseList);
    public void findAll();

    Page<Course> findPage(Page<Course> objectPage, Integer courseID, String courseName);

}
