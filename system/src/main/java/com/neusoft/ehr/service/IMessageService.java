package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.po.ViewMessagesPo;

import java.util.Locale;

public interface IMessageService {
    ViewMessagesPo selectMessage(String code, Locale locale);
}
