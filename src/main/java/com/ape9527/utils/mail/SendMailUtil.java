package com.ape9527.utils.mail;

import com.ape9527.utils.mail.model.Sender;
import com.ape9527.utils.mail.model.Receiver;
import com.sun.mail.util.MailSSLSocketFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

/**
 * 邮件发送工具类
 *
 * @author YuanShuai[apeblog@163.com]
 */
@Slf4j
public class SendMailUtil {

    /**
     * 发送普通邮件
     *
     * @param sender 发送人信息
     * @param receiver 接收人及发送信息
     * @return true or false
     */
    public static boolean sendMail(Sender sender, Receiver receiver) {
        receiver.setTitle(sender.getSignature() + receiver.getTitle());
        receiver.setContent(sender.getSignature() + receiver.getContent());
        log.info("发送普通邮件");
        log.info("发送方: {}",sender.getUsername());
        log.info("接收方: {}",receiver.getEmail());
        log.info("邮件标题: {}",receiver.getTitle());
        log.info("邮件内容: {}",receiver.getContent());
        try {
            Properties props = new Properties();
            MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            props.put("mail.smtp.ssl.socketFactory", sf);
            props.put("mail.smtp.ssl.enable", "true");
            props.setProperty("mail.transport.protocol", sender.getProtocol());
            props.setProperty("mail.smtp.host", sender.getHost());
            props.setProperty("mail.smtp.port", sender.getHost());
            props.setProperty("mail.smtp.auth", "true");
            props.setProperty("mail.user", sender.getUsername());
            props.setProperty("mail.password", sender.getPassword());

            Authenticator authenticator = new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    String userName = props.getProperty("mail.user");
                    String password = props.getProperty("mail.password");
                    return new PasswordAuthentication(userName, password);
                }
            };
            Session mailSession = Session.getInstance(props, authenticator);
            MimeMessage message = new MimeMessage(mailSession);
            String username = props.getProperty("mail.user");
            InternetAddress form = new InternetAddress(username);
            message.setFrom(form);

            InternetAddress toAddress = new InternetAddress(receiver.getEmail());
            message.setRecipient(Message.RecipientType.TO, toAddress);

            message.setSubject(receiver.getTitle());

            message.setContent(receiver.getContent(), "text/html;charset=UTF-8");
            Transport.send(message);

            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

}
