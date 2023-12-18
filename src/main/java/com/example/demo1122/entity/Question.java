package com.example.demo1122.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModel;
import lombok.Data;

import java.io.Serializable;

@Data
@ApiModel(value = "Question对象", description = "")
@TableName(value = "question")
public class Question implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId
    @TableField(value = "questionID")
    private Integer questionID;
    @TableField(value = "conversationID")
    private Integer conversationID;
    @TableField(value = "questionTime")
    private String questionTime;
    @TableField(value = "questionContent")
    private String questionContent;
    @TableField(value = "questionEvaluation")
    private Integer questionEvaluation;
    @TableField(value = "answerContent")
    private String answerContent;
}
