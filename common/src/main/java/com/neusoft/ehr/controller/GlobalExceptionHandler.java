package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.ServiceException;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author 曹健伟
 * <p>
 * 全局异常处理
 */
@RestControllerAdvice
public class GlobalExceptionHandler extends BaseController {
    /**
     * GlobalExceptionHandler构造函数
     *
     * @param messageSource 国际化组件
     */
    public GlobalExceptionHandler(MessageSource messageSource) {
        super(messageSource);
    }

    /**
     * 处理业务一场
     *
     * @param e 一场
     * @return 响应错误
     */
    @ExceptionHandler(ServiceException.class)
    public Response<Void> serviceException(ServiceException e) {
        return failure(e.getServiceCode());
    }
}
