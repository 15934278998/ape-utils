package com.ape9527.utils.openai.model;

import lombok.Data;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ImageRequest {

    /**
     * 图片描述
     */
    private String prompt;

    /**
     * 返回图片数量
     */
    private Integer n = 1;

    /**
     * 返回图片分辨率 256x256 or 512x512 or 1024x1024
     */
    private String size = "256x256";

    /**
     * 返回图片格式 url or b64_json
     */
    private String response_format = "url";

    public ImageRequest(String prompt) {
        this.prompt = prompt;
    }

}
