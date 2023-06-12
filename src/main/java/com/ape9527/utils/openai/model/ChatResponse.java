package com.ape9527.utils.openai.model;

import lombok.Data;

import java.util.List;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ChatResponse {

    private String id;

    private String object;

    /**
     * 时间戳
     */
    private Long created;

    /**
     * 模型
     */
    private String model;

    /**
     * 生成的消息内容
     */
    private List<ChatChoice> choices;

    /**
     * tokens用量
     */
    private ChatUsage usage;

}
