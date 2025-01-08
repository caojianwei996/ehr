package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.ViewMessagesPo;
import com.neusoft.ehr.mapper.MessagesMapper;
import com.neusoft.ehr.mapper.ViewMessagesMapper;
import com.neusoft.ehr.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Locale;

@RequiredArgsConstructor
@Service
public class MessageService implements IMessageService {
    private final MessagesMapper messagesMapper;
    private final ViewMessagesMapper viewMessagesMapper;

    @Override
    public ViewMessagesPo selectMessage(String code, Locale locale) {
        return viewMessagesMapper.selectOne(
                Wrappers.<ViewMessagesPo>lambdaQuery()
                        .eq(ViewMessagesPo::getCode, code)
                        .eq(ViewMessagesPo::getLanguage, locale.getLanguage())
                        .eq(ViewMessagesPo::getContry, locale.getCountry())
        );
    }
}
