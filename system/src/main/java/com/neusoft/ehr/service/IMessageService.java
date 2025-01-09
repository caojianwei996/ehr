package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.po.ViewMessagesPo;

import java.util.Locale;

/**
 * 消息业务
 *
 * @author 曹健伟
 */
public interface IMessageService {
    void insertMessage(ViewMessagesPo po);

    void deleteMessage(ViewMessagesPo po);

    void updateMessage(ViewMessagesPo po);

    IPage<ViewMessagesPo> selectMessage(ViewMessagesPo po, Integer page, Integer size);

    ViewMessagesPo selectMessage(String code, Locale locale);
}
