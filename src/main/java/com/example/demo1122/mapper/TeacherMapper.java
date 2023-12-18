package com.example.demo1122.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.entity.Teacher;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-12-08
 */
@Mapper
public interface TeacherMapper extends BaseMapper<Teacher> {
    @Results(id = "teacherResultMap", value = {
            @Result(property = "teacherID", column = "teacherID"),
            @Result(property = "password", column = "password"),
            @Result(property = "identity", column = "identity"),
            @Result(property = "name", column = "name")
    })


    @Select("SELECT teacherID, identity, name FROM teacher")
    List<Teacher> findAll();

    @Insert("INSERT into teacher(teacherID, password,identity,name) VALUES (#{teacherID}, #{password}," +
            " #{identity}, #{name})")
    int insert(Teacher teacher);

    @Update("UPDATE teacher SET password=#{password}, identity=#{identity}, name=#{name} WHERE teacherID=#{teacherID}")
    int update(Teacher teacher);


    @Delete("delete from teacher where teacherID = #{teacherID}")
    String deleteById(@Param("teacherID") String teacherID);

    @Select("SELECT COUNT(*) FROM teacher WHERE teacherID = #{teacherID}")
    boolean isTeacherIDExists(Teacher teacher);


    @Select("select * from teacher where name like #{name} limit #{pageNum}, #{pageSize}")
    List<Teacher> selectPage(Integer pageNum, Integer pageSize, String name);

    @Select("select count(*) from teacher where name like concat('%', #{name}, '%') ")
    Integer selectTotal(String name);

    @Select("select * from teacher where name like #{teacherID} limit #{pageNum}, #{pageSize}")
    List<Teacher> selectidPage(Integer pageNum, Integer pageSize, String teacherID);

    @Select("select count(*) from teacher where teacherID like concat('%', #{teacherID}, '%') ")
    Integer selectidTotal(String teacherID);

    @ResultMap("teacherResultMap")
    Page<Teacher> findPage(Page<Teacher> page, @Param("teacherID") String teacherID, @Param("name") String name);

}
