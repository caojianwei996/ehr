package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.MessageDto;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.MessagesPo;
import com.neusoft.ehr.mapper.MessagesMapper;
import com.neusoft.ehr.service.IMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Locale;

/**
 * 消息业务实现
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class MessageService implements IMessageService {
    /**
     * 消息仓库组件
     */
    private final MessagesMapper messagesMapper;

    /**
     * 插入新消息
     *
     * @param po 消息
     */
    @Override
    public void insertMessage(MessageDto po) {
        if (messagesMapper.exists(
                Wrappers.<MessagesPo>lambdaQuery()
                        .eq(MessagesPo::getCode, po.getCode())
                        .eq(MessagesPo::getLanguage, po.getLanguage())
        )) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        MessagesPo messagesPo = new MessagesPo();
        messagesPo.setCode(po.getCode());
        messagesPo.setLanguage(po.getLanguage());
        messagesPo.setText(po.getText());
        messagesMapper.insert(messagesPo);
    }

    /**
     * 删除消息
     *
     * @param po 消息
     */
    @Override
    public void deleteMessage(MessageDto po) {
        messagesMapper.delete(
                Wrappers.<MessagesPo>lambdaQuery()
                        .eq(MessagesPo::getCode, po.getCode())
                        .eq(MessagesPo::getLanguage, po.getLanguage())
        );
    }

    @Override
    public void updateMessage(MessageDto po) {
        MessagesPo viewMessagesPo = messagesMapper.selectOne(
                Wrappers.<MessagesPo>lambdaQuery()
                        .eq(MessagesPo::getCode, po.getCode())
                        .eq(MessagesPo::getLanguage, po.getLanguage())
        );
        if (viewMessagesPo != null) {
            MessagesPo messagesPo = messagesMapper.selectById(viewMessagesPo.getId());
            messagesPo.setText(po.getText());
            messagesMapper.updateById(messagesPo);
        }
    }

    @Override
    public IPage<MessagesPo> selectMessage(MessageDto po, Integer page, Integer size) {
        LambdaQueryWrapper<MessagesPo> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(po.getCode())) {
            wrapper.eq(MessagesPo::getCode, po.getCode());
        }
        if (StringUtils.hasText(po.getLanguage())) {
            wrapper.eq(MessagesPo::getLanguage, po.getLanguage());
        }
        return messagesMapper.selectPage(Page.of(page, size), wrapper);
    }

    /**
     * 查询消息
     *
     * @param code 消息码
     * @return 消息
     */
    @Override
    public MessagesPo selectMessage(String code, Locale locale) {
        return messagesMapper.selectOne(
                Wrappers.<MessagesPo>lambdaQuery()
                        .eq(MessagesPo::getCode, code)
                        .eq(MessagesPo::getLanguage, locale.getLanguage())
        );
    }
}
