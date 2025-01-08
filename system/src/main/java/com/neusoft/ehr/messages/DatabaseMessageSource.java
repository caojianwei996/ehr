package com.neusoft.ehr.messages;

import com.neusoft.ehr.entity.po.ViewMessagesPo;
import com.neusoft.ehr.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.AbstractMessageSource;
import org.springframework.stereotype.Component;

import java.text.MessageFormat;
import java.util.Locale;

@RequiredArgsConstructor
@Component("messageSource")
public class DatabaseMessageSource extends AbstractMessageSource {
    private final IMessageService messageService;

    @Override
    protected MessageFormat resolveCode(String code, Locale locale) {
        ViewMessagesPo viewMessagesPo = messageService.selectMessage(code, locale);
        if (viewMessagesPo != null) {
            return new MessageFormat(viewMessagesPo.getText(), locale);
        } else {
            viewMessagesPo = messageService.selectMessage(code, Locale.CHINA);
            if (viewMessagesPo != null) {
                return new MessageFormat(viewMessagesPo.getText(), locale);
            } else {
                return new MessageFormat(code, locale);
            }
        }
    }
}
