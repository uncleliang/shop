package com.niit.util;

/**
 * @program: shop
 * @description:
 * @author: hanliang
 * @create: 2020-02-07 08:49
 **/
public class ConsUtil {

    // 用户状态：未激活
    public static final int USER_UN_ACTIVE=0;
    // 用户状态：已激活
    public static final int USER_ACTIVE=1;

    // 激活邮件发件人
    public static final String ACTIVE_EMAIL_SENDER="uncleliang2020@163.com";

    // 激活邮件主题
    public static final String ACTIVE_EMAIL_SUBJECT="NIIT商场新用户激活邮件";

    //  每页显示数据行数
    public static final Integer PAGE_SIZE=12;

    // 购物车cookie名字
    public static final String COOKIE_CART_NAME = "_cn";

    // 购物车 redis key
    public static final String KEY_CART = "cart";

    // 购物车 redis 保存时间 月
    public static final Long TIME_MONTH = 30L;

    // 购物车 redis 保存时间 月
    public static final Long TIME_WEEK = 7L;

    // 购物车 redis 保存时间 天
    public static final Long TIME_DAY = 1L;
}
