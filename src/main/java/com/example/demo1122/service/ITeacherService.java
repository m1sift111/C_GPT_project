package com.example.demo1122.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Teacher;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-08
 */
public interface ITeacherService extends IService<Teacher> {
    public ResponseResult saveTeacher(Teacher teacher);
    public ResponseResult saveTeacherList(List<Teacher> teacherList);
    public void findAll();

    Page<Teacher> findPage(Page<Teacher> objectPage, String teacherID, String name);

}
