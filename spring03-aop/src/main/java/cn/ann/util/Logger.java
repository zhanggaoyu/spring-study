package cn.ann.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * create by 88475 with IntelliJ IDEA on 2019-11-2 14:40
 */
@Component("logger")
@Aspect
public class Logger {
    @Pointcut("execution(* cn.ann.service..*.*(..))")
    private void logPointcut(){}

//    @Before("logPointcut()")
    public void beforeLogging(){
        System.out.println("before logging run...");
    }

//    @AfterReturning("logPointcut()")
    public void afterReturningLogging(){
        System.out.println("afterReturning logging run...");
    }

//    @AfterThrowing("logPointcut()")
    public void afterThrowingLogging(){
        System.out.println("afterThrowing logging run...");
    }

//    @After("logPointcut()")
    public void afterLogging(){
        System.out.println("after logging run...");
    }

    @Around("logPointcut()")
    public Object aroundLogging(ProceedingJoinPoint pjp) {
        try {
            System.out.println("前置 ...");
            Object ret = pjp.proceed();
            System.out.println("后置 ...");
            return ret;
        } catch (Throwable throwable) {
            System.out.println("异常 ...");
            throw new RuntimeException(throwable);
        } finally {
            System.out.println("最终 ...");
        }
    }
}
