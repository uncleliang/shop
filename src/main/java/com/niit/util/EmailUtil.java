package com.niit.util;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 09:28
 **/
public class EmailUtil {

    /**
     * 组织邮件信息并发送邮件
     *
     * @param mailSender
     * @param toAddress
     * @param fromAddress
     * @param subject
     * @param htmlBody
     * @throws MessagingException
     */
    public  static void sendActiveEmail(JavaMailSender mailSender, String toAddress, String fromAddress, String subject, String htmlBody)
            throws MessagingException {

        // 创建邮件对象 MimeMessage
        MimeMessage message = mailSender.createMimeMessage();

        //  邮件组装工具类
        MimeMessageHelper helper = new MimeMessageHelper(message, "UTF-8");
        // 设置收件人
        helper.setTo(toAddress);
        // 设置发件人地址
        helper.setFrom(fromAddress);
        // 设置发送邮件的内容
        helper.setText(htmlBody, true);
        // 设置发送邮件的主题
        helper.setSubject(subject);
        mailSender.send(message);
    }
}
