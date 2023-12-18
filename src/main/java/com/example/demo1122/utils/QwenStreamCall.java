package com.example.demo1122.utils;

import com.alibaba.dashscope.aigc.generation.Generation;
import com.alibaba.dashscope.aigc.generation.GenerationResult;
import com.alibaba.dashscope.aigc.generation.models.QwenParam;
import com.alibaba.dashscope.common.Message;
import com.alibaba.dashscope.common.Role;
import com.alibaba.dashscope.exception.ApiException;
import com.alibaba.dashscope.exception.InputRequiredException;
import com.alibaba.dashscope.exception.NoApiKeyException;
import com.alibaba.dashscope.utils.Constants;
import com.alibaba.dashscope.utils.JsonUtils;
import io.reactivex.Flowable;

import java.util.Arrays;

public class QwenStreamCall {
    public static String streamCallWithMessage(String questionContent)
            throws NoApiKeyException, ApiException, InputRequiredException {
        Constants.apiKey="sk-3e7738dd94e34b7a8d9d4747e6730afd"; // 后续将api key放入配置文件中
        Generation gen = new Generation();
        Message userMsg = Message
                .builder()
                .role(Role.USER.getValue())
                .content(questionContent)
                .build();
        QwenParam param =
                QwenParam.builder().model(Generation.Models.QWEN_PLUS).messages(Arrays.asList(userMsg))
                        .resultFormat(QwenParam.ResultFormat.MESSAGE)
                        .topP(0.8)
                        .enableSearch(true)
                        .incrementalOutput(true) // get streaming output incrementally
                        .build();
        Flowable<GenerationResult> result = gen.streamCall(param);
        StringBuilder fullContent = new StringBuilder();
        // blockingForEach,在每一次响应后block获取输出append
        result.blockingForEach(message -> {
            fullContent.append(message.getOutput().getChoices().get(0).getMessage().getContent());
            System.out.println(JsonUtils.toJson(message));
        });
        System.out.println("Full content: \n" + fullContent.toString());
        return fullContent.toString();
    }

    public static Flowable<GenerationResult> streamCallWithMessage_test(String questionContent)
        throws NoApiKeyException, ApiException, InputRequiredException {
            Constants.apiKey="sk-3e7738dd94e34b7a8d9d4747e6730afd"; // 后续将api key放入配置文件中
            Generation gen = new Generation();
            Message userMsg = Message
                    .builder()
                    .role(Role.USER.getValue())
                    .content(questionContent)
                    .build();
            QwenParam param =
                    QwenParam.builder().model(Generation.Models.QWEN_PLUS).messages(Arrays.asList(userMsg))
                            .resultFormat(QwenParam.ResultFormat.MESSAGE)
                            .topP(0.8)
                            .enableSearch(true)
                            .incrementalOutput(true) // get streaming output incrementally
                            .build();
            Flowable<GenerationResult> result = gen.streamCall(param);
            return result;
    }
}
