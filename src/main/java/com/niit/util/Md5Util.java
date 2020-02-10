package com.niit.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 08:27
 **/
public class Md5Util {

    public static final String TYPE_MD5 = "md5";

    //  "密钥"
    public static final String KEY = "NIIT1234";

    /**
     * 对字符串进行MD5加密
     *
     * @param password
     * @return
     */
    public static String md5(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance(TYPE_MD5);

            StringBuffer buffer = new StringBuffer();
            buffer.append(KEY.charAt(1));
            buffer.append(KEY.charAt(3));
            buffer.append(password);
            buffer.append(KEY.charAt(4));
            buffer.append(KEY.charAt(6));

            byte[] bytes = md.digest(buffer.toString().getBytes());

            String str = Base64.getEncoder().encodeToString(bytes);

            return str;
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(md5("123456"));
    }
}


