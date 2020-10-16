package com.sun.myquartz.aspect;

import com.alibaba.fastjson.JSON;
import com.sun.myquartz.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.swing.*;
import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 日志的切面
 * @author zcm
 */
@Slf4j
@Component
@Aspect
public class LogAspect {

    /**
     * 切的注解
     */
    @Pointcut("@annotation(com.sun.myquartz.annotation.LogAnnotation)")
    public void pointCut(){

    }


    @Before("pointCut()")
    public void doBefore(JoinPoint joinPoint){
        //1. 通过反射，获取方法
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        //2. 获取方法
        Method method = methodSignature.getMethod();
        //3. 获取方法上的注解,如果不是，则pass
        LogAnnotation logAnnotation = method.getAnnotation(LogAnnotation.class);
        //4。判断
        if (Objects.isNull(logAnnotation)){
            return;
        }
        //5. 正确捕获注解，开始打印
        String methodName = method.getDeclaringClass().getSimpleName() + "." +  method.getName();
        log.info("start {},入参 {}",methodName, JSON.toJSONString(joinPoint.getArgs()));
    }


    @AfterReturning(value = "pointCut()",returning = "result")
    public void afterReturn(JoinPoint joinPoint,Object result){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        LogAnnotation annotation = method.getAnnotation(LogAnnotation.class);
        if (Objects.isNull(annotation)) {
            return;
        }
        String methodName = method.getDeclaringClass().getSimpleName() + "." + method.getName();
        log.info("end {}：响应：{}", methodName, JSON.toJSONString(result));
    }

}
