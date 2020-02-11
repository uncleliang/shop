package com.niit.aop;

import com.alibaba.fastjson.JSON;
import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @program: shop
 * @description: 打印日志的切面
 * @author: hanliang
 * @create: 2020-02-11 09:49
 **/

@Component  // 声明成Spring的组建，让自动扫描可以扫描到这个类
@Aspect // 切面注解
public class LogAspect {

    public  Logger logger =null;


    @Autowired
    HttpServletRequest request;

    /**
     *   execution(public * *(..)) 所有 public开头的方法
     *   execution(* set*(..))     所有 set开头的方法
     *   execution(* com.xyz.service.AccountService.*(..))  这个包中，这个类中所有的方法
     *   execution(* com.xyz.service.*.*(..))               这个包中，所有的类，所有的方法
     *   execution(* com.xyz.service..*.*(..))              这个包以及子包中的所有类，所有方法
     * */
    // 切点
    @Pointcut("execution(* com.niit..*.*(..)))")
    public void printLog(){}


//    @Before("printLog()") // 在切点对应的方法执行之前执行的代码
//    public  void before(){
//
//        System.out.println("before");
//    }
//
//
//    @After("printLog()") // 在切点对应的方法执行之后执行的代码
//    public  void after(){
//        System.out.println("after");
//    }


    @Around("printLog()")
    public  Object around(ProceedingJoinPoint pj){ // 拦截器  pj表示当前正在执行的符合切点的这个方法对象
        Object result=null;
        try {


            // 类
            Class<?> clazz = pj.getTarget().getClass();

            // 方法名
            String methodName = pj.getSignature().getName();

            // 参数列表
            String agrs = JSON.toJSONString(pj.getArgs());

            // ip地址
            String ip = getIpAddress(request);

            logger = Logger.getLogger(clazz);

            logger.debug(methodName+"方法开始执行");

            result = pj.proceed();
            // 返回值
            String ret = JSON.toJSONString(pj.getArgs());
            logger.debug(methodName+"方法执行结束，IP："+ip+" 方法参数："+agrs+",方法返回值："+ret);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        return result;
    }

//    @AfterReturning("printLog()") // 在切点对应的方法return之后执行的代码
//    public  void afterReturning(){
//        System.out.println("afterReturning");
//    }
//
//    @AfterThrowing("printLog()") // 在切点对应的方法出现Exception之后执行的代码
//    public  void afterThrowing(){
//        System.out.println("afterThrowing");
//    }

    /**
     * 获取IP地址的方法
     * @param request   传一个request对象下来
     * @return
     */
    public static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return ip;
    }
}
