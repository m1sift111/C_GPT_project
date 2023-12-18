package com.example.demo1122.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Teacher;
import com.example.demo1122.mapper.TeacherMapper;
import com.example.demo1122.service.ITeacherService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
 * @since 2023-12-08
 */
@Service
public class TeacherServiceImpl extends ServiceImpl<TeacherMapper, Teacher> implements ITeacherService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    private final TeacherMapper teacherMapper;

    @Autowired
    public TeacherServiceImpl(TeacherMapper teacherMapper) {
        this.teacherMapper = teacherMapper;
    }


    @Override
    public ResponseResult saveTeacher(Teacher teacher) {
        if(isEmpty(teacher.getTeacherID())){
            return new ResponseResult(313, "工号不能为空");
        }
        else{
            if (teacherMapper.isTeacherIDExists(teacher)) {
                // 教师ID已存在，执行更新操作
                teacherMapper.update(teacher);
                return new ResponseResult(315, "修改成功");
            } else {
                // 教师ID不存在，执行插入操作
                teacherMapper.insert(teacher);
                return new ResponseResult(310, "插入成功");
            }
        }
    }

    // 新增保存学生列表的方法
    public ResponseResult saveTeacherList(List<Teacher> teacherList) {

        try {
            for (Teacher teacher : teacherList) {
                // 设置默认密码和身份信息

                teacher.setPassword(passwordEncoder.encode("123456"));
                teacher.setIdentity(true);
                // 调用保存单个学生的方法
                saveTeacher(teacher);
            }
            return new ResponseResult(300, "批量插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(500, "批量插入失败", null);
        }
    }

    @Override
    public void findAll() {
        teacherMapper.findAll();

    }

    @Override
    public Page<Teacher> findPage(Page<Teacher> page, String teacherID, String name) {
        return teacherMapper.findPage(page, teacherID, name);
    }

}
