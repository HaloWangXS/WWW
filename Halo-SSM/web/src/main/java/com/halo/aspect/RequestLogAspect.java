package com.halo.aspect;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.halo.annotation.RequestLog;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.aop.framework.ReflectiveMethodInvocation;
import org.springframework.stereotype.Component;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Arrays;

@Aspect
@Slf4j
@Component
public class RequestLogAspect {

    /**
     * @Pointcut 声明频繁使用的切点表达式
     * 我们使用execution指示器,选择了Preformance的perform方法
     * *开头代表这不关心返回值是什么
     * ..代表着切点要忽略入参
     * @Pointcut("execution(* com.souche.aop.aspect.Preformance.perform(..))")
     * ps: Pointcut里面的值可以是某个包路径, 某个类, 亦或是某个方法
     *     除此之外 还可以是一些被自定义注解标示的方法
     */
    @Pointcut("@annotation(com.halo.annotation.RequestLog)")
    public void pointCut() {
        log.info("这里没有实际作用滴");
    }

    @Around("pointCut()")
    public Object aroundAspect(ProceedingJoinPoint joinPoint) {
        Method method;
        String methodName = null;
        String annotationName = null;
        try {
            MethodInvocationProceedingJoinPoint methodPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
            Field proxy;
            ReflectiveMethodInvocation j = null;
            try {
                proxy = methodPoint.getClass().getDeclaredField("methodInvocation");
                proxy.setAccessible(true);
                j = (ReflectiveMethodInvocation) proxy.get(methodPoint);
            } catch (Exception e) {
                try {
                    return joinPoint.proceed();
                } catch (Throwable throwable) {
                    throwable.printStackTrace();
                }

            }
            method = j.getMethod();
            Annotation annotation = method.getAnnotation(RequestLog.class);
            methodName = method.getName();
            annotationName = annotation == null ? "" : ((RequestLog) annotation).value();
            Object[] args = joinPoint.getArgs();
            StringBuilder sb = new StringBuilder();
            if (null != args) {
                Arrays.asList(args).forEach(param -> sb.append("[").append(JSON.toJSONString(param, SerializerFeature.WriteMapNullValue)).append("] "));
            }
            log.info("");
            log.info("vvvvvvvvvvvvvvvvvvvvvvvvvvvv start vvvvvvvvvvvvvvvvvvvvvvvvvvvvvv");
            log.info("调用方法 {}({}),参数列表 {}", methodName, annotationName, sb);

        } catch (Exception e) {
            log.error("error:", e);
        }
        try {
            long timeStart = System.currentTimeMillis();
            Object result = joinPoint.proceed();
            log.info("调用方法 {}({}) 耗时 {}ms, 返回结果 {}", methodName, annotationName, System.currentTimeMillis() - timeStart, JSON.toJSONString(result));
            log.info("^^^^^^^^^^^^^^^^^^^^^^^^^^^^ end ^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
            log.info("");
            return result;
        } catch (Throwable throwable) {
            log.error("调用方法 {}({}) 出错", methodName, annotationName, throwable);
        }
        return null;
    }
}
