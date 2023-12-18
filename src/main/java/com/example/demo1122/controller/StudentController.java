package com.example.demo1122.controller;

import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.DTO.StudentDTO;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Conversation;
import com.example.demo1122.entity.Question;
import com.example.demo1122.entity.Student;
import com.example.demo1122.service.IConversationService;
import com.example.demo1122.service.IQuestionService;
import com.example.demo1122.service.IStudentService;
import io.reactivex.Flowable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
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
 * @since 2023-12-07
 */
@Controller
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private IStudentService studentService;
    @Autowired
    private IConversationService conversationService;
    @Autowired
    private IQuestionService questionService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(StudentController.class);


    @PostMapping("/upload")
    @ResponseBody
    public ResponseResult uploadExcel(@RequestParam("File") MultipartFile file) {
        if (file.isEmpty()) {

            return new ResponseResult(120, "文件为空");
        }
        try {
            InputStream inputStream = file.getInputStream();

            // 使用EasyExcel读取Excel文件数据
            List<Student> students = readExcel(inputStream);

            // 在这里可以对读取到的数据进行处理，例如保存到数据库
            studentService.saveStudentList(students);
            return new ResponseResult(126, "文件上传成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(127, "文件上传失败");
        }
    }

    private List<Student> readExcel(InputStream inputStream) {
        // 使用EasyExcel读取Excel文件数据
        List<Student> students = new ArrayList<>();

        // 使用 EasyExcel.read 方法传入输入流和数据分析监听器
        EasyExcel.read(inputStream, new AnalysisEventListener<Map<Integer, String>>() {
            @Override
            public void invoke(Map<Integer, String> data, AnalysisContext context) {
                // 'data' 是一个映射，包含每一行的单元格值，键是列索引，值是单元格的值
                // 假设 'studentID' 在第一列，'name' 在第二列
                String studentID = data.get(0);
                String name = data.get(1);

                Student student = new Student();
                student.setStudentID(studentID);
                student.setName(name);

                students.add(student);
            }

            @Override
            public void doAfterAllAnalysed(AnalysisContext context) {
                // 在读取完成后执行一些操作，如果需要的话
            }
        }).sheet().doRead();

        return students;
    }

    // 新增和修改

    @PostMapping("/saveorupdate")
    @ResponseBody
    public ResponseResult save(@RequestBody Student student) {
        try {
            String password = passwordEncoder.encode(student.getPassword());
            student.setPassword(password);

            // 新增或者更新
            return studentService.saveStudent(student);
        } catch (Exception e) {
            // 打印异常信息
            e.printStackTrace();
            // 处理异常，将异常信息放入ResponseResult对象返回
            return new ResponseResult<>(500, "Internal Server Error", null);
        }
    }



    @GetMapping("/findall")
    @ResponseBody
    public List<StudentDTO> findAll() {
        List<Student> students = studentService.list();
        List<StudentDTO> studentDTOs = convertToDTOList(students);
        return studentDTOs;
    }

    private List<StudentDTO> convertToDTOList(List<Student> students) {
        // 编写转换逻辑，将 Student 转换为 StudentDTO
        List<StudentDTO> studentDTOs = new ArrayList<>();

        for (Student student : students) {
            StudentDTO studentDTO = new StudentDTO();
            studentDTO.setStudentID(student.getStudentID());
            studentDTO.setIdentity(student.getIdentity());
            studentDTO.setName(student.getName());

            // 如果有其他需要转换的字段，可以继续添加

            studentDTOs.add(studentDTO);
        }

        return studentDTOs;
    }

//删除单个数据

    @DeleteMapping("/{studentID}")
    @ResponseBody
    public ResponseResult delete(@PathVariable String studentID) {

        try {
            studentService.removeById(studentID);
            return new ResponseResult(156, "删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseResult(157, "删除失败");
        }
    }

    //批量删除
    @PostMapping("/del/batch")
    public boolean deleteBatch(@RequestBody List<String> studentIDs) { // [1,2,3]

        return studentService.removeByIds(studentIDs);
    }
    // 分页查询
    @GetMapping("/page2")
    @ResponseBody
    public ResponseResult findPage2(@RequestParam Integer pageNum,
                           @RequestParam Integer pageSize,
                           @RequestParam(defaultValue = "") String studentID,
                           @RequestParam(defaultValue = "") String name) {

    try {    studentService.findPage(new Page<>(pageNum, pageSize), studentID,name);
        return new ResponseResult(166, "查询成功");
    } catch (Exception e) {
        e.printStackTrace();
        return new ResponseResult(167, "查询失败");
    }
    }

    //cly added
    // conversation
    // 创建conversation
    @PostMapping("/createconversation")
    @ResponseBody
    public ResponseResult createConversation(@RequestBody Conversation conversation){
         return conversationService.createConversation(conversation);
    }

    // 返回某学生id下所有conversation
    @PostMapping("/allconversation/{studentID}")
    @ResponseBody
    public List<Conversation> getAllConversation(@PathVariable String studentID){
        return conversationService.selectStudentConversation(studentID);
    }

    // 查询一个学生创建的conversation数量
    @PostMapping("/conversationnum/{studentID}")
    @ResponseBody
    public ResponseResult getConversationNum(@PathVariable String studentID){
        return conversationService.getConversationNum(studentID);
    }

    //学生评价conversation质量
    @PostMapping("/evaluateconversation")
    @ResponseBody
    public ResponseResult evaluateConversation(@RequestBody Conversation conversation){
        return conversationService.evaluateConversation(conversation);
    }

    //questionService
    // 学生ask接口，返回千问模型输出
    @PostMapping("/ask")
    @ResponseBody
    public ResponseResult askLLM(@RequestBody Question question){
        try {
            return questionService.askLLM(question);
        } catch (Exception e){
            e.printStackTrace();
            return new ResponseResult(0, "failed to ask LLM");
        }
    }

    @PostMapping(value = "/asktest", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    @ResponseBody
    public Flowable<GenerationResult> askLLM_test(@RequestBody Question question) throws NoApiKeyException, InputRequiredException {
        return questionService.askLLM_test(question);
    }

    // 返回某conversation下所有question answer内容
    @PostMapping("/showconversation/{conversationID}")
    @ResponseBody
    public ResponseResult showConversation(@PathVariable Integer conversationID){
        return questionService.showConversation(conversationID);
    }

    // 查询学生在某个对话下的所有question
    @PostMapping("/allquestion/{conversationID}")
    @ResponseBody
    public List<Question> getAllQuestion(@PathVariable Integer conversationID){
        return questionService.selectConversationQuestion(conversationID);
    }

    // 查询学生在某个对话下的questionNum
    @PostMapping("/questionNum/{conversationID}")
    @ResponseBody
    public ResponseResult getQuestionNum(@PathVariable Integer conversationID){
        return questionService.getQuestionNum(conversationID);
    }

    // 学生评价question质量
    @PostMapping("/evaluatequestion")
    @ResponseBody
    public ResponseResult evaluateQuestion(@RequestBody Question question){
        return questionService.evaluateQuestion(question);
    }

}
