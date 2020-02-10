package com.niit.model;

/**
 * @program: shop
 * @description: Ajax方法返回值统一处理
 * @author: hanliang
 * @create: 2020-02-07 13:45
 **/
public class ResultBean<T> {

    // 错误代码：0 表示成功
    private int code=0;

    // 返回的消息
    private String message="";

    private T data;

    // 成功
    public static ResultBean SUCCESS = new ResultBean(0);

    // 失败
    public static ResultBean ERROR = new ResultBean(-1);


    public ResultBean() {
    }

    public ResultBean(int code) {
        this.code = code;
    }

    public ResultBean(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
