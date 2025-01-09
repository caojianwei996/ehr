package com.neusoft.ehr.interceptor.auth;

import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.util.token.TokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Collections;

@RequiredArgsConstructor
@Component
@Order(Ordered.HIGHEST_PRECEDENCE+1)
public class AuthorizationInterceptor implements HandlerInterceptor {
    private static final ThreadLocal<LoginVo> THREAD_LOCAL = new InheritableThreadLocal<>();
    public static LoginVo getCurrentUser() {
        return THREAD_LOCAL.get();
    }

    private final PathMatcher pathMatcher = new AntPathMatcher();
    private final TokenUtil tokenUtil;
    private final AuthenticationProperties properties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            THREAD_LOCAL.set(
                    tokenUtil.decode(
                            request.getHeader("Authorization").substring(7), LoginVo.class
                    )
            );
            return true;
        } catch (Throwable e) {
            for (String path : properties
                    .getPermit().getOrDefault(
                            HttpMethod.valueOf(request.getMethod().toUpperCase()),
                            Collections.emptyList()
                    )
            ) {
                if (pathMatcher.match(path, request.getRequestURI())) {
                    response.setStatus(HttpStatus.UNAUTHORIZED.value());
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        THREAD_LOCAL.remove();
    }
}
