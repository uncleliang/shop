package com.niit.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * @program: shop
 * @description: Cookie操作工具类
 * @author: hanliang
 * @create: 2020-02-12 09:16
 **/
public class CookieUtil {

    /**
     *  根据cookie名字查询 cookie
     * @param request
     * @param cookieName cooki名字
     * @return
     */
    public static Cookie getCookie(HttpServletRequest request,String cookieName){

        Cookie cookie=null;

        //  获取全部Cookie
        Cookie[] cookies = request.getCookies();

        if(cookies!=null&&cookies.length>0){
            for(Cookie c : cookies){
                if(cookieName.equals(c.getName())){
                    cookie = c;
                    break;
                }
            }
        }

        return cookie;
    }

    /**
     * 添加cookie的值
     * @param response
     * @param cookieName
     * @param cookieValue
     */
    public static void  setCookie(HttpServletResponse response,String cookieName, String cookieValue){

        try {
            if(cookieValue!=null){
                cookieValue = URLEncoder.encode(cookieValue, "utf-8");
            }

        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie(cookieName,cookieValue);
        cookie.setMaxAge(60*60*24*30);
        cookie.setPath("/");

        response.addCookie(cookie);
    }
}
