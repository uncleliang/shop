package com.niit.aop;

import com.fasterxml.jackson.databind.util.JSONPObject;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

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

            logger = Logger.getLogger(pj.getClass());

            logger.debug("方法执行之前");

            result = pj.proceed();

            logger.debug("方法执行之后，类名:"+pj.getTarget().getClass()+",方法名："+pj.getSignature().getName()+",方法参数："+pj.getArgs()+",方法返回值："+result);
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
}
