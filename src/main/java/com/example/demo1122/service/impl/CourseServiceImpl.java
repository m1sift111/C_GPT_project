package com.example.demo1122.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Course;
import com.example.demo1122.mapper.CourseMapper;
import com.example.demo1122.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baomidou
 * @since 2023-12-09
 */
@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {
    @Autowired

    private final CourseMapper courseMapper;

    @Autowired
    public CourseServiceImpl(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }


    @Override
    public ResponseResult saveCourse(Course course) {
        if(course.getCourseID()==null){
            return new ResponseResult(303, "课程号不能为空");
        }
        else {
            if (courseMapper.isCourseIDExists(course)) {
                // ID已存在，执行更新操作
                courseMapper.update(course);
                return new ResponseResult(305, "修改成功");
            } else {
                // ID不存在，执行插入操作
                courseMapper.insert(course);
                return new ResponseResult(300, "插入成功");
            }
        }
    }

    // 新增保存学生列表的方法
    public ResponseResult saveCourseList(List<Course> courseList) {

        try {
                for (Course course : courseList) {
                // 设置默认密码和身份信息

                saveCourse(course);
            }
            return new ResponseResult(300, "批量插入成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult<>(500, "批量插入失败", null);
        }
    }

    @Override
    public void findAll() {
        courseMapper.findAll();

    }

    @Override
    public Page<Course> findPage(Page<Course> page, Integer courseID, String courseName) {
        return courseMapper.findPage(page, courseID, courseName);
    }
}
