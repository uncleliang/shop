package com.niit.service.impl;

import com.niit.dao.UserMapper;
import com.niit.model.User;
import com.niit.service.IUserService;
import com.niit.util.ConsUtil;
import com.niit.util.EmailUtil;
import com.niit.util.Md5Util;
import com.niit.util.UUIDUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-06 14:25
 **/
@Service
public class UserService implements IUserService {

    @Autowired
    UserMapper dao;

    @Autowired
    JavaMailSender mailSender;

    @Override
    public boolean checkUsername(String username) {
        return dao.getByUsername(username)!=null;
    }

    @Override
    public boolean insertSelective(User record) {

        // 用户默认状态是未激活
        record.setState(ConsUtil.USER_UN_ACTIVE);

        // 生产一个激活码
        String code = UUIDUtil.getActiveCode();
        record.setCode(code);

        // 密码加密
        record.setPassword(Md5Util.md5(record.getPassword()));

        // 如果数据添加成功 就发送邮件
        if(dao.insertSelective(record)>0){

            try {
                // 发送邮件
                EmailUtil.sendActiveEmail(mailSender,record.getEmail(),ConsUtil.ACTIVE_EMAIL_SENDER,ConsUtil.ACTIVE_EMAIL_SUBJECT,getEmailContent(code));
            } catch (MessagingException e) {
                // 发送失败
                return false;
            }
            return true;
        }else{
            return false;
        }
    }

    /**
     * 激活
     * @param code
     * @return
     */
    @Override
    public boolean active(String code) {
        return dao.active(code)>0;
    }

    /**
     *  判断用户名和密码是否正确
     * @param username
     * @param password
     * @return
     */
    @Override
    public User getByUsernameAndPassword(String username, String password) {
        return dao.getByUsernameAndPassword(username,Md5Util.md5(password));
    }


    private String getEmailContent(String code){

        String email = "欢迎注册NIIT商城!</br>" +
                "请单击以下超链接完成激活：<a href='http://localhost:8081/user/active?code="+code+"'>"+code+"</a>";
        return email;
    }
}
