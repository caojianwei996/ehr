package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.MessageDto;
import com.neusoft.ehr.entity.po.MessagesPo;

import java.util.Locale;

/**
 * 消息业务
 *
 * @author 曹健伟
 */
public interface IMessageService {
    void insertMessage(MessageDto po);

    void deleteMessage(MessageDto po);

    void updateMessage(MessageDto po);

    IPage<MessagesPo> selectMessage(MessageDto po, Integer page, Integer size);

    MessagesPo selectMessage(String code, Locale locale);
}
