package com.example.demo1122.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Conversation;
import com.example.demo1122.mapper.ConversationMapper;
import com.example.demo1122.service.IConversationService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class ConversationServiceImpl extends ServiceImpl<ConversationMapper, Conversation> implements IConversationService {
    @Resource
    private  ConversationMapper conversationMapper;

    @Override
    public ResponseResult createConversation(Conversation conversation){
        conversationMapper.insert(conversation);
        Map<String, Integer> conversationMap = new HashMap<>();
        conversationMap.put("conversationID", conversation.getConversationID());
        return new ResponseResult(1, "success", conversationMap);
    }

    @Override
    public List<Conversation> selectStudentConversation(String studentID){
        return conversationMapper.selectAllByStudentID(studentID);
    }

    @Override
    public ResponseResult getConversationNum(String studentID){
        Integer conversationNum = conversationMapper.getConversationNum(studentID);
        Map<String, Integer> conversationNumMap = new HashMap<>();
        conversationNumMap.put("conversationNum", conversationNum);
        return new ResponseResult(1, "success", conversationNumMap);
    }

    @Override
    public ResponseResult evaluateConversation(Conversation conversation) {
        conversationMapper.update(conversation);
        return new ResponseResult(1, "success");
    }

}
