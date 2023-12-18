package com.example.demo1122.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.demo1122.entity.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;

import java.util.List;

@Mapper
public interface QuestionMapper extends BaseMapper<Question> {
    @Results(id = "questionResultMap", value = {
            @Result(property = "questionID", column = "questionID"),
            @Result(property = "conversationID", column = "conversationID"),
            @Result(property = "questionTime", column = "questionTime"),
            @Result(property = "questionContent", column = "questionContent"),
            @Result(property = "questionEvaluation", column = "questionEvaluation"),
            @Result(property = "answerContent", column = "answerContent")
    })

    int insert(Question question);

    boolean update(Question question);

    List<Question> selectAllByConversationID(@Param("conversationID") Integer conversationID);

    int getQuestionNum(@Param("conversationID") Integer conversationID);
}
