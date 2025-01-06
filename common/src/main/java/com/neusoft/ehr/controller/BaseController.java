package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.ServiceCode;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

public abstract class BaseController {
    private final MessageSource messageSource;

    protected BaseController(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    private String getMessage(String name) {
        return messageSource.getMessage(name, null, LocaleContextHolder.getLocale());
    }

    protected Response<Void> success() {
        return new Response<>(ServiceCode.SUCCESS.ordinal(), getMessage(ServiceCode.SUCCESS.name()), null);
    }

    protected <T> Response<T> success(T data) {
        return new Response<>(ServiceCode.SUCCESS.ordinal(), getMessage(ServiceCode.SUCCESS.name()), data);
    }

    protected Response<Void> failure(ServiceCode serviceCode) {
        return new Response<>(serviceCode.ordinal(), getMessage(serviceCode.name()), null);
    }
}
