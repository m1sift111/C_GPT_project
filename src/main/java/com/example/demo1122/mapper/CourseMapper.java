package com.example.demo1122.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.demo1122.entity.Course;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author baomidou
 * @since 2023-12-09
 */
@Mapper
public interface CourseMapper extends BaseMapper<Course> {
    @Results(id = "courseResultMap", value = {
            @Result(property = "courseID", column = "courseID"),
            @Result(property = "courseName", column = "courseName")
    })

    @Select("SELECT courseID, courseName FROM course")
    List<Course> findAll();

    @Insert("INSERT into course(courseID, courseName) VALUES (#{courseID}, #{courseName})")
    int insert(Course course);

    @Update("UPDATE course SET courseName=#{courseName}, WHERE courseID=#{courseID}")
    int update(Course course);


    @Delete("delete from course where courseID = #{courseID}")
    String deleteById(@Param("courseID") Integer courseID);

    @Select("SELECT COUNT(*) FROM course WHERE courseID = #{courseID}")
    boolean isCourseIDExists(Course course);


    @ResultMap("coursetResultMap")
    Page<Course> findPage(Page<Course> page, @Param("courseID") Integer courseID, @Param("courseName") String courseName);

}
