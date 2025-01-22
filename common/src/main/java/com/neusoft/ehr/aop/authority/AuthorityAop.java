package com.neusoft.ehr.aop.authority;

import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AuthorityAop {
    @Pointcut("execution(com.neusoft.ehr.entity.Response com.neusoft.ehr.controller.*.*(..)) && @annotation(Authority)")
    public void pointcut() {
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint pjp) throws Throwable {
        Authority annotation = ((MethodSignature) pjp.getSignature()).getMethod().getAnnotation(Authority.class);
        LoginVo currentUser = AuthorizationInterceptor.getCurrentUser();
        if (annotation != null && (currentUser == null || annotation.value() > currentUser.getAuthority())) {
            throw new ServiceException(ServiceCode.FAILURE);
        }
        return pjp.proceed();
    }
}
