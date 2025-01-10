package com.neusoft.ehr.interceptor.log;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Order(Ordered.HIGHEST_PRECEDENCE)
@Component
@Slf4j
public class LogInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<Long> THREAD_LOCAL = new InheritableThreadLocal<>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        THREAD_LOCAL.set(System.currentTimeMillis());
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("{} {} {} {}ms", request.getRemoteAddr(), request.getMethod(), request.getRequestURI(), System.currentTimeMillis() - THREAD_LOCAL.get());
    }
}
