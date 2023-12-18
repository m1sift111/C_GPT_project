package com.example.demo1122.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo1122.entity.Conversation;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface ConversationMapper extends BaseMapper<Conversation> {
    @Results(id = "conversationResultMap", value = {
            @Result(property = "conversationID", column = "conversationID"),
            @Result(property = "startTime", column = "startTime"),
            @Result(property = "endTime", column = "endTime"),
            @Result(property = "conversationEvaluation", column = "conversationEvaluation"),
            @Result(property = "classID", column = "classID"),
            @Result(property = "studentID", column = "studentID"),
            @Result(property = "chapterID", column = "chapterID")
    })

    int insert(Conversation conversation);

    boolean update(Conversation conversation);

    @Select("SELECT COUNT(*) FROM conversation WHERE conversationID = #{conversationID}")
    boolean isConversationIDExists(Conversation conversation);

    @Select("SELECT classID, chapterID, conversationID FROM conversation WHERE studentID = #{studentID}")
    List<Conversation> selectAllByStudentID(String studentID);

    int getConversationNum(@Param("studentID") String studentID);
}
