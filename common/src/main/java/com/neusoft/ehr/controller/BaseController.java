package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.ServiceCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author 曹健伟
 * <p>
 * 基本Controler
 */
public abstract class BaseController {
    /**
     * 国际化组件
     */
    private final MessageSource messageSource;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected BaseController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * 获取国际化信息
     *
     * @param name 国际化信息键
     * @return 国际化信息值
     */
    private String getMessage(String name) {
        return messageSource.getMessage(name, null, LocaleContextHolder.getLocale());
    }

    /**
     * 响应成功
     *
     * @return Response对象
     */
    protected Response<Void> success() {
        return new Response<>(ServiceCode.SUCCESS.ordinal(), getMessage(ServiceCode.SUCCESS.name()), null);
    }

    /**
     * 响应成功
     *
     * @param data 数据
     * @param <T>  数据类型
     * @return Response对象
     */
    protected <T> Response<T> success(T data) {
        return new Response<>(ServiceCode.SUCCESS.ordinal(), getMessage(ServiceCode.SUCCESS.name()), data);
    }

    /**
     * 响应失败
     *
     * @param serviceCode 业务码
     * @return Response对象
     */
    protected Response<Void> failure(ServiceCode serviceCode) {
        return new Response<>(serviceCode.ordinal(), getMessage(serviceCode.name()), null);
    }
}
