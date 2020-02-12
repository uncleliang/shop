package com.niit.controller;

import com.alibaba.fastjson.JSON;
import com.niit.model.Cart;
import com.niit.model.CartItem;
import com.niit.model.ResultBean;
import com.niit.model.User;
import com.niit.service.IUserService;
import com.niit.service.impl.RedisService;
import com.niit.util.ConsUtil;
import com.niit.util.CookieUtil;
import com.niit.util.MessageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

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

    @Autowired
    RedisService redisService;

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
    public ResultBean login(String username, String password,
                            String verifyCode,
                            HttpServletRequest request,
                            HttpServletResponse response,
                            HttpSession session){

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

        // 合并购物车
        combineCart(request, response, user);

        return new ResultBean();
    }

    private void combineCart(HttpServletRequest request, HttpServletResponse response, User user) {
        Cart cookieCart=null;   //cookie里面的购物车
        Cart redisCart=null;    //redis里面的购物车
        Cart cart = new Cart(); //合并之后的总的购物车
        // 获取 cookie 购物车
        Cookie cookie = CookieUtil.getCookie(request,ConsUtil.COOKIE_CART_NAME);
        if(cookie!=null){
            String value = "";
            try {
                if(cookie.getValue()!=null){
                    value = URLDecoder.decode(cookie.getValue(),"utf-8");
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }

            if(value!=null){
                cookieCart = JSON.parseObject(value,Cart.class);
            }
        }

        // 获取 redis购物车
        String data = redisService.get(ConsUtil.KEY_CART+":"+user.getUid());
        if(data!=null) {
            // 转换成购物车对象
            redisCart = JSON.parseObject(data, Cart.class);
        }

        // 合并
        // 总金额
        cart.setTotal(cookieCart.getTotal()+redisCart.getTotal());

        Map<Integer,CartItem> resultMap = new HashMap<>();

        // 将redis购物车中国全部数据加入
        resultMap.putAll(redisCart.getCartMap());

        for(Integer key : cookieCart.getCartMap().keySet()){
            if(!resultMap.containsKey(key)){
                resultMap.put(key,cookieCart.getCartMap().get(key));
            }else{
                int redisQuantity = redisCart.getCartMap().get(key).getQuantity();
                int cookieQuantity = cookieCart.getCartMap().get(key).getQuantity();
                redisQuantity = redisQuantity+cookieQuantity;
                redisCart.getCartMap().get(key).setQuantity(redisQuantity);
            }
        }

        cart.setCartMap(resultMap);

        //  将合并结果写入redis
        redisService.set(ConsUtil.KEY_CART+":"+user.getUid(),JSON.toJSONString(cart),ConsUtil.TIME_WEEK);

        // 清空cookie中的购物车
        CookieUtil.setCookie(response,ConsUtil.COOKIE_CART_NAME,null);
    }
}