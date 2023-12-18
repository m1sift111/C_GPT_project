package com.example.demo1122.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Course;
import com.example.demo1122.service.ICourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author baomidou
 * @since 2023-12-09
 */
@Controller
@RequestMapping("/course")
public class CourseController {
    @Autowired
    private ICourseService courseService;

    private static final Logger logger = LoggerFactory.getLogger(CourseController.class);


    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult uploadExcel(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {

            return new ResponseResult(120, "文件为空");
        }
        try {
            InputStream inputStream = file.getInputStream();

            // 使用EasyExcel读取Excel文件数据
            List<Course> courses = readExcel(inputStream);

            // 在这里可以对读取到的数据进行处理，例如保存到数据库
            courseService.saveCourseList(courses);
            return new ResponseResult(126, "文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(127, "文件上传失败");
        }
    }

    private List<Course> readExcel(InputStream inputStream) {
        // 使用EasyExcel读取Excel文件数据
        List<Course> courses = new ArrayList<>();

        // 使用 EasyExcel.read 方法传入输入流和数据分析监听器
        EasyExcel.read(inputStream, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // 'data' 是一个映射，包含每一行的单元格值，键是列索引，值是单元格的值
                // 假设 'ID' 在第一列，'name' 在第二列
                Integer courseID = Integer.valueOf(data.get(0));
                String courseName = data.get(1);

                Course course = new Course();
                course.setCourseID(courseID);
                course.setCourseName(courseName);

                courses.add(course);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 在读取完成后执行一些操作，如果需要的话
            }
        }).sheet().doRead();

        return courses;
    }

    // 新增和修改

    @PostMapping("/saveorupdate")
    @ResponseBody
    public ResponseResult save(@RequestBody Course course) {
        try {
           // 新增或者更新
            return courseService.saveCourse(course);
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 处理异常，将异常信息放入ResponseResult对象返回
            return new ResponseResult<>(500, "Internal Server Error", null);
        }
    }



    @GetMapping("/findall")
    @ResponseBody
    public List<Course> findAll() {
        List<Course> courses = courseService.list();
        return courses;
    }

//删除单个数据

    @DeleteMapping("/{courseID}")
    @ResponseBody
    public ResponseResult delete(@PathVariable Integer courseID) {

        try {
            courseService.removeById(courseID);
            return new ResponseResult(156, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(157, "删除失败");
        }
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<Integer> courseIDs) { // [1,2,3]

        return courseService.removeByIds(courseIDs);
    }
    // 分页查询
    @GetMapping("/page2")
    @ResponseBody
    public ResponseResult findPage2(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") Integer courseID,
                                    @RequestParam(defaultValue = "") String name) {

        try {    courseService.findPage(new Page<>(pageNum, pageSize), courseID,name);
            return new ResponseResult(166, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(167, "查询失败");
        }
    }

}
