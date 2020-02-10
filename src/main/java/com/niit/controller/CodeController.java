package com.niit.controller;

import com.niit.util.CodeUtil;
import com.niit.util.ConsUtil;
import com.niit.util.EmailUtil;
import com.sun.mail.util.MailSSLSocketFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @program: shop
 * @description: 验证码控制器
 * @author: hanliang
 * @create: 2020-02-06 09:45
 **/
@Controller
@RequestMapping("/code")
public class CodeController {

    @RequestMapping("/generateCode")
    public void generateCode(HttpSession session, HttpServletResponse response) throws IOException {

        // session记录生产的验证码  以便在注册时候跟用户输入的验证码进行比对
       BufferedImage image =  CodeUtil.getCode(session);

        // response把生产的图片 返回，显示到界面
       OutputStream os = response.getOutputStream();

       ImageIO.write(image,"JPEG",os);

       os.flush();
       os.close();
    }
}
