package com.ape9527.utils.mail.model;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;

/**
 * 邮件发送人
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Data
public class Sender {

    /** 邮件服务器 */
    private String host;

    /** 邮箱号 */
    private String username;

    /** 密钥 */
    private String password;

    /** 发送端口 */
    private String port;

    /** 发送协议 */
    private String protocol;

    /** 邮件签名 */
    private String signature;

}
