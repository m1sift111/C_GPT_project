package com.example.demo1122.service.impl;


import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Question;
import com.example.demo1122.mapper.QuestionMapper;
import com.example.demo1122.service.IQuestionService;
import com.example.demo1122.utils.QwenStreamCall;
import io.reactivex.Flowable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements IQuestionService {
    @Resource
    private  QuestionMapper questionMapper;

    @Override
    public ResponseResult askLLM(Question question) {
        String questionContent = question.getQuestionContent();
        try {
            String LLMResult = QwenStreamCall.streamCallWithMessage(questionContent);
            question.setAnswerContent(LLMResult);
            questionMapper.insert(question);
            Map<String, String> questionMap = new HashMap<>();
            questionMap.put("answer", LLMResult);
            return new ResponseResult(1,"success",questionMap);
        } catch (ApiException | NoApiKeyException | InputRequiredException e) {
            return new ResponseResult(0, e.getMessage());
        }
    }

    @Override
    public List<Question> selectConversationQuestion(Integer conversationID){
        return questionMapper.selectAllByConversationID(conversationID);
    }

    @Override
    public ResponseResult getQuestionNum(Integer conversationID){
        Integer questionNum = questionMapper.getQuestionNum(conversationID);
        Map<String, Integer> questionNumMap = new HashMap<>();
        questionNumMap.put("questionNum", questionNum);
        return new ResponseResult(1, "success", questionNumMap);
    }

    @Override
    public ResponseResult evaluateQuestion(Question question){
        questionMapper.update(question);
        return new ResponseResult(1, "success");
    }

    @Override
    public ResponseResult showConversation(Integer conversationID) {
        QueryWrapper<Question> questionQueryWrapper = new QueryWrapper<>();
        questionQueryWrapper.eq("conversationID", conversationID);
        List<Question> questionList = questionMapper.selectList(questionQueryWrapper);
        Map<String, List<Question>> showConversationMap = new HashMap<>();
        showConversationMap.put("data", questionList);
        return new ResponseResult(1, "success", showConversationMap);
    }

    @Override
    public Flowable<GenerationResult> askLLM_test(Question question) throws NoApiKeyException, InputRequiredException {
        String questionContent = question.getQuestionContent();
        return QwenStreamCall.streamCallWithMessage_test(questionContent);
    }

}
