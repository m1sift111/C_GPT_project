package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;


@Data
@ApiModel(value = "Conversation对象", description = "")
@TableName(value = "conversation")
public class Conversation implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(type = IdType.AUTO) // 修改了数据库，先设置了conversationID自增，再作为question表外键
    @TableField(value = "conversationID")
    private Integer conversationID;
    @TableField(value = "startTime")
    private String startTime;
    @TableField(value = "endTime")
    private String endTime;
    @TableField(value = "conversationEvaluation")
    private Integer conversationEvaluation;
    @TableField(value = "classID")
    private Integer classID;
    @TableField(value = "studentID")
    private String studentID;
    @TableField(value = "chapterID")
    private Integer chapterID;

}
