package com.ape9527.utils.openai;

import com.alibaba.fastjson.JSONObject;
import com.ape9527.utils.http.HttpsUtil;
import com.ape9527.utils.json.JsonUtil;
import com.ape9527.utils.openai.model.*;
import com.ape9527.utils.string.StringUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author YuanShuai[apeblog@163.com]
 */
public class OpenAiUtil {

    /**
     * 请求代理
     */
    public static String BASE_URL;

    /**
     * API_KEY
     */
    public static String API_KEY;

    /**
     * model
     */
    public static String MODEL;

    /**
     * Creates a completion for the chat message
     */
    private static final String CREATE_CHAT_COMPLETION = "/v1/chat/completions";

    /**
     * Creates an image given a prompt.
     */
    private static final String CREATE_IMAGE = "/v1/images/generations";

    /**
     * Creates a completion for the chat message
     *
     * @param messages
     * @return
     */
    public static ChatResponse createChatCompletion(List<ChatMessage> messages) {
        ChatRequest request = new ChatRequest(messages);
        return createChatCompletion(request);
    }

    /**
     * Creates a completion for the ChatRequest
     *
     * @param request
     * @return
     */
    public static ChatResponse createChatCompletion(ChatRequest request) {
        if (StringUtil.isEmpty(request.getModel())) {
            request.setModel(MODEL);
        }
        String response = HttpsUtil.sendSslPost(
                BASE_URL + CREATE_CHAT_COMPLETION,
                JSONObject.toJSONString(request),
                null,
                null,
                "Authorization=Bearer " + API_KEY
        );
        return JsonUtil.parseObject(response, ChatResponse.class);
    }

    /**
     * Creates an image given a prompt.
     *
     * @param prompt
     * @return
     */
    public static ImageResponse createImage(String prompt) {
        ImageRequest request = new ImageRequest(prompt);
        return createImage(request);
    }

    /**
     * Creates an image given a ImageRequest.
     *
     * @param request
     * @return
     */
    public static ImageResponse createImage(ImageRequest request) {
        String response = HttpsUtil.sendSslPost(
                BASE_URL + CREATE_IMAGE,
                JSONObject.toJSONString(request),
                null,
                null,
                "Authorization=Bearer " + API_KEY
        );
        return JsonUtil.parseObject(response, ImageResponse.class);
    }

}
