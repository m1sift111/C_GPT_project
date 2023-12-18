package com.example.demo1122.service;

import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Question;
import io.reactivex.Flowable;

import java.util.List;

public interface IQuestionService extends IService<Question> {
    ResponseResult askLLM(Question question) throws NoApiKeyException, InputRequiredException;

    List<Question> selectConversationQuestion(Integer conversationID);

    ResponseResult getQuestionNum(Integer conversationID);

    ResponseResult evaluateQuestion(Question question);

    ResponseResult showConversation(Integer conversationID);

    //test
    Flowable<GenerationResult> askLLM_test(Question question) throws NoApiKeyException, InputRequiredException;
}
