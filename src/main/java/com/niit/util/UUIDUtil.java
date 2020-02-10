package com.niit.util;

import java.util.UUID;

/**
 * UUID 工具类
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 08:26
 **/
public class UUIDUtil {

    /**
     * 随机生成激活码
     *
     * @return
     */
    public static String getActiveCode(){

        return UUID.randomUUID().toString().replace("-","");
    }

    public static void main(String[] args) {
        System.out.println(getActiveCode());
    }
}
