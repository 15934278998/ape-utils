package com.ape9527.utils.openai.model;

import lombok.Data;

import java.util.List;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ImageResponse {

    /**
     * 时间戳
     */
    private Long created;

    /**
     * 生成的图片集合
     */
    private List<ImageData> data;

}
