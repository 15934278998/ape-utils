package com.ape9527.utils.openai.model;

import lombok.Data;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ChatMessage {

    /**
     * 角色，用户为user，ai为assistant
     */
    private String role;

    /**
     * 消息内容
     */
    private String content;

}
