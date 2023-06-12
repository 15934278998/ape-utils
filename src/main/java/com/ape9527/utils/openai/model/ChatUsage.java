package com.ape9527.utils.openai.model;

import lombok.Data;

/**
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class ChatUsage {

    /**
     * 提问消耗的tokens
     */
    private Integer prompt_tokens;

    /**
     * 回答消耗的tokens
     */
    private Integer completion_tokens;

    /**
     * 总tokens
     */
    private Integer total_tokens;

}
