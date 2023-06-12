package com.ape9527.utils.mail.model;

import lombok.Data;
import lombok.ToString;

/**
 * 收件人模型
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Data
@ToString
public class Receiver {
    /** 收件人邮箱号 */
    private String email;

    /** 邮件标题 */
    private String title;

    /** 邮件内容 */
    private String content;

    public Receiver() {
    }

    public Receiver(String email) {
        this.email = email;
    }
}
