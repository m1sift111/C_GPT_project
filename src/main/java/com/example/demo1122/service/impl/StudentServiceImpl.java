package com.example.demo1122.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Student;
import com.example.demo1122.mapper.StudentMapper;
import com.example.demo1122.service.IStudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.apache.logging.log4j.util.Strings.isEmpty;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-07
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements IStudentService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    private final StudentMapper studentMapper;

    @Autowired
    public StudentServiceImpl(StudentMapper studentMapper) {
        this.studentMapper = studentMapper;
    }


    @Override
    public ResponseResult saveStudent(Student student) {
        if(isEmpty(student.getStudentID())){
            return new ResponseResult(303, "学号不能为空");
        }
        else {
            if (studentMapper.isStudentIDExists(student)) {
                // 学生ID已存在，执行更新操作
                studentMapper.update(student);
                return new ResponseResult(305, "修改成功");
            } else {
                // 学生ID不存在，执行插入操作
                studentMapper.insert(student);
                return new ResponseResult(300, "插入成功");
            }
        }
    }

    // 新增保存学生列表的方法
    public ResponseResult saveStudentList(List<Student> studentList) {

        try {
            for (Student student : studentList) {
                // 设置默认密码和身份信息

                student.setPassword(passwordEncoder.encode("123456"));
                student.setIdentity(true);
                // 调用保存单个学生的方法
                saveStudent(student);
            }
            return new ResponseResult(300, "批量插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(500, "批量插入失败", null);
        }
    }

    @Override
    public void findAll() {
        studentMapper.findAll();

    }

    @Override
    public Page<Student> findPage(Page<Student> page, String studentID, String name) {
        return studentMapper.findPage(page, studentID, name);
    }



}
