package com.example.demo1122.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.entity.Student;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-12-07
 */
@Mapper
public interface StudentMapper extends BaseMapper<Student> {

    @Results(id = "studentResultMap", value = {
            @Result(property = "studentID", column = "studentID"),
            @Result(property = "password", column = "password"),
            @Result(property = "identity", column = "identity"),
            @Result(property = "name", column = "name")
    })

//    @Select("SELECT studentID, password, identity, name FROM student")
//    List<Student> findAll();
    @Select("SELECT studentID, identity, name FROM student")
    List<Student> findAll();

    @Insert("INSERT into student(studentID, password,identity,name) VALUES (#{studentID}, #{password}," +
            " #{identity}, #{name})")
    int insert(Student student);

    @Update("UPDATE student SET password=#{password}, identity=#{identity}, name=#{name} WHERE studentID=#{studentID}")
    int update(Student student);


    @Delete("delete from student where studentID = #{studentID}")
    String deleteById(@Param("studentID") String studentID);

    @Select("SELECT COUNT(*) FROM student WHERE studentID = #{studentID}")
    boolean isStudentIDExists(Student student);


    @Select("select * from student where name like #{name} limit #{pageNum}, #{pageSize}")
    List<Student> selectPage(Integer pageNum, Integer pageSize, String name);

    @Select("select count(*) from student where name like concat('%', #{name}, '%') ")
    Integer selectTotal(String name);

    @Select("select * from student where name like #{studentID} limit #{pageNum}, #{pageSize}")
    List<Student> selectidPage(Integer pageNum, Integer pageSize, String studentID);

    @Select("select count(*) from student where studentID like concat('%', #{studentID}, '%') ")
    Integer selectidTotal(String studentID);

    @ResultMap("studentResultMap")
    Page<Student> findPage(Page<Student> page, @Param("studentID") String studentID, @Param("name") String name);
}
