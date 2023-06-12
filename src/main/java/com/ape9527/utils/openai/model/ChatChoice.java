package com.ape9527.utils.openai.model;

import lombok.Data;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ChatChoice {

    /**
     * 索引
     */
    private Integer index;

    /**
     * 消息
     */
    private ChatMessage message;

    /**
     * 结束原因
     */
    private String finish_reason;

}
