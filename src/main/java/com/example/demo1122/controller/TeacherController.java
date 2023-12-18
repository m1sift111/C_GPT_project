package com.example.demo1122.controller;


import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.DTO.TeacherDTO;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Teacher;
import com.example.demo1122.service.ITeacherService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
 * @since 2023-12-08
 */
@Controller
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private ITeacherService teacherService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(TeacherController.class);

//Excel导入
    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult uploadExcel(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {

            return new ResponseResult(120, "文件为空");
        }
        try {
            InputStream inputStream = file.getInputStream();

            // 使用EasyExcel读取Excel文件数据
            List<Teacher> teachers = readExcel(inputStream);

            // 在这里可以对读取到的数据进行处理，例如保存到数据库
            teacherService.saveTeacherList(teachers);
            return new ResponseResult(126, "文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(127, "文件上传失败");
        }
    }

    private List<Teacher> readExcel(InputStream inputStream) {
        // 使用EasyExcel读取Excel文件数据
        List<Teacher> teachers = new ArrayList<>();

        // 使用 EasyExcel.read 方法传入输入流和数据分析监听器
        EasyExcel.read(inputStream, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // 'data' 是一个映射，包含每一行的单元格值，键是列索引，值是单元格的值
                // 假设 'teacherID' 在第一列，'name' 在第二列
                String teacherID = data.get(0);
                String name = data.get(1);

                Teacher teacher = new Teacher();
                teacher.setTeacherID(teacherID);
                teacher.setName(name);

                teachers.add(teacher);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 在读取完成后执行一些操作，如果需要的话
            }
        }).sheet().doRead();

        return teachers;
    }

    // 新增和修改

    @PostMapping("/saveorupdate")
    @ResponseBody
    public ResponseResult save(@RequestBody Teacher teacher) {
        try {
            String password = passwordEncoder.encode(teacher.getPassword());
            teacher.setPassword(password);

            // 新增或者更新
            return teacherService.saveTeacher(teacher);
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 处理异常，将异常信息放入ResponseResult对象返回
            return new ResponseResult<>(500, "Internal Server Error", null);
        }
    }



    @GetMapping("/findall")
    @ResponseBody
    public List<TeacherDTO> findAll() {
        List<Teacher> teachers = teacherService.list();
        List<TeacherDTO> teacherDTOs = convertToDTOList(teachers);
        return teacherDTOs;
    }

    private List<TeacherDTO> convertToDTOList(List<Teacher> teachers) {
        // 编写转换逻辑，将 Teacher 转换为 TeacherDTO
        List<TeacherDTO> teacherDTOS = new ArrayList<>();

        for (Teacher teacher : teachers) {
            TeacherDTO teacherDTO = new TeacherDTO();
            teacherDTO.setTeacherID(teacher.getTeacherID());
            teacherDTO.setIdentity(teacher.getIdentity());
            teacherDTO.setName(teacher.getName());

            // 如果有其他需要转换的字段，可以继续添加

            teacherDTOS.add(teacherDTO);
        }

        return teacherDTOS;
    }

//删除单个数据

    @DeleteMapping("/{teacherID}")
    @ResponseBody
    public ResponseResult delete(@PathVariable String teacherID) {

        try {
            teacherService.removeById(teacherID);
            return new ResponseResult(156, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(157, "删除失败");
        }
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<String> teacherIDs) { // [1,2,3]

        return teacherService.removeByIds(teacherIDs);
    }
    // 分页查询
    @GetMapping("/page2")
    @ResponseBody
    public ResponseResult findPage2(@RequestParam Integer pageNum,
                                    @RequestParam Integer pageSize,
                                    @RequestParam(defaultValue = "") String teacherID,
                                    @RequestParam(defaultValue = "") String name) {

        try {    teacherService.findPage(new Page<>(pageNum, pageSize), teacherID,name);
            return new ResponseResult(166, "查询成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(167, "查询失败");
        }
    }


}
