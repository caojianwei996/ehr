package com.neusoft.ehr.messages;

import com.neusoft.ehr.entity.po.MessagesPo;
import com.neusoft.ehr.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 数据库消息源
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Component("messageSource")
public class DatabaseMessageSource extends AbstractMessageSource {
    /**
     * 消息业务组件
     */
    private final IMessageService messageService;

    /**
     * 解析消息码
     *
     * @param code   消息码
     * @param locale 地区
     * @return 格式化消息
     */
    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        MessagesPo messagesPo = messageService.selectMessage(code, locale);
        if (messagesPo != null) {
            return new MessageFormat(messagesPo.getText(), locale);
        } else {
            messagesPo = messageService.selectMessage(code, Locale.US);
            if (messagesPo != null) {
                return new MessageFormat(messagesPo.getText(), Locale.US);
            } else {
                return new MessageFormat(code, Locale.ENGLISH);
            }
        }
    }
}
