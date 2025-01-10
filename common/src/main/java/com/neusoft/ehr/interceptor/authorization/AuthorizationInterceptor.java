package com.neusoft.ehr.interceptor.authorization;

import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.token.TokenUtil;
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
@Order(Ordered.HIGHEST_PRECEDENCE + 1)
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
            HttpMethod method = HttpMethod.resolve(request.getMethod());
            String uri = request.getRequestURI();
            for (String path : properties.getPermit().getOrDefault(method, Collections.emptyList())) {
                if (pathMatcher.match(path, uri)) {
                    return true;
                }
            }
            response.setStatus(HttpStatus.UNAUTHORIZED.value());
            return false;
        }
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        THREAD_LOCAL.remove();
    }
}
