package com.niit.controller;

import com.niit.model.ResultBean;
import com.niit.model.User;
import com.niit.service.IUserService;
import com.niit.util.ConsUtil;
import com.niit.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * @program: shop
 * @description: 用户 控制器
 * @author: hanliang
 * @create: 2020-02-06 09:15
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    IUserService userService;

    /**
     *  打开注册界面
     * @return
     */
    @RequestMapping("/goRegister")
    public String openRegister() {
        return "register";
    }


    /**
     *  打开登陆界面
     * @return
     */
    @RequestMapping("/goLogin")
    public String goLogin() {
        return "login";
    }

    /**
     * 验证用户名是否存在
     * @param username
     * @return
     */
    @RequestMapping("/checkUsername")
    @ResponseBody
    public ResultBean checkUsername(String username) {

        if(userService.checkUsername(username)){
            return ResultBean.SUCCESS;
        }else{
            return ResultBean.ERROR;
        }
    }

    /**
     * 比对验证码是否正确
     * @param verifyCode
     * @param session
     * @return
     */
    @RequestMapping("/checkVerifyCode")
    @ResponseBody
    public boolean checkVerifyCode(String verifyCode, HttpSession session) {

        String sessionCode = (String) session.getAttribute("CODE");
        System.out.println(sessionCode);
        // 忽略大小写，比较验证码是否正确
        return sessionCode.equalsIgnoreCase(verifyCode);
    }

    /**
     * 完成注册
     * @param user
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public boolean register(User user) {

        return userService.insertSelective(user);
    }

    /**
     * 显示注册结果界面
     * @param email
     * @param modelMap
     * @return
     */
    @RequestMapping("/registerResult")
    public String registerResult(String email,ModelMap modelMap) {

        modelMap.addAttribute("email",email);

        return "registerResult";
    }

    /**
     *  用户激活
     * @param code
     * @return
     */
    @RequestMapping("/active")
    public String active(String code) {

        if(userService.active(code)){
            return "login";
        }
        return "error";
    }


    /**
     * 实现登陆
     * @param username
     * @param password
     * @param verifyCode
     * @return
     */
    @RequestMapping("/login")
    @ResponseBody
    public ResultBean login(String username, String password, String verifyCode,HttpSession session){

        // 1. 检查用户名和密码是否正确
        User user = userService.getByUsernameAndPassword(username,password);

        // 如果登陆失败
        if(user==null){
            return new ResultBean(-1,MessageUtil.E_LOGIN_ERROR);
        }

        // 2. 检查用户是否激活
        if(user.getState()!=ConsUtil.USER_ACTIVE){
            return new ResultBean(-1,MessageUtil.E_USER_UNACTIVE);
        }
        // 3. 检查验证码码是否正确
        String sessionCode = (String)session.getAttribute("CODE");
        if(!sessionCode.equalsIgnoreCase(verifyCode)){
            return new ResultBean(-1,MessageUtil.E_VERIFYCODE_ERROR);
        }

        // 4. 将用户信息存入Session
        session.setAttribute("USER",user);

        return new ResultBean();
    }
}