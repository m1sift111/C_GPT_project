package com.example.demo1122.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.demo1122.domain.ResponseResult;
import com.example.demo1122.entity.Conversation;

import java.util.List;

public interface IConversationService extends IService<Conversation> {
//    ResponseResult saveConversation(Conversation conversation);

    ResponseResult createConversation(Conversation conversation);

    List<Conversation> selectStudentConversation(String studentID);



    ResponseResult getConversationNum(String studentID);

    ResponseResult evaluateConversation(Conversation conversation);

}
