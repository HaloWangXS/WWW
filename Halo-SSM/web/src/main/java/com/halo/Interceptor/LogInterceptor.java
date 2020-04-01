package com.halo.Interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
public class LogInterceptor extends HandlerInterceptorAdapter {

    private ThreadLocal<Long> longLocal = new ThreadLocal<>();
    private ThreadLocal<String> stringLocal = new ThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HandlerMethod method = (HandlerMethod) handler;
        log.info("方法：{} 开始时间：{}", method.getMethod(), System.currentTimeMillis());
        longLocal.set(System.currentTimeMillis());
        stringLocal.set(method.getMethod().getName());
        return true;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("方法 {} 执行耗时：{} ms", stringLocal.get(), System.currentTimeMillis() - longLocal.get());
    }
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("接口方法 {} 结束", stringLocal.get(), System.currentTimeMillis() - longLocal.get());
    }
}
