package com.example.demo1122.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Student;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-07
 */
public interface IStudentService extends IService<Student> {

    public ResponseResult saveStudent(Student student);
    public ResponseResult saveStudentList(List<Student> studentList);
    public void findAll();

    Page<Student> findPage(Page<Student> objectPage, String studentID, String name);
}
