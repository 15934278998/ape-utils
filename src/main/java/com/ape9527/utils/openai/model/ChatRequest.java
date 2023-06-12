package com.ape9527.utils.openai.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ChatRequest {

    /**
     * 模型
     */
    private String model;

    /**
     * 对话列表
     */
    private List<ChatMessage> messages;

    public ChatRequest(){
        this.messages = new ArrayList<>();
    }

    public ChatRequest(List<ChatMessage> messages){
        this.messages = messages;
    }

    public void addMessage(ChatMessage message){
        this.messages.add(message);
    }

}
