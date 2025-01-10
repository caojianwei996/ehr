package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.CountriesPo;
import com.neusoft.ehr.entity.po.LanguagesPo;
import com.neusoft.ehr.entity.po.MessagesPo;
import com.neusoft.ehr.entity.po.ViewMessagesPo;
import com.neusoft.ehr.mapper.CountriesMapper;
import com.neusoft.ehr.mapper.LanguagesMapper;
import com.neusoft.ehr.mapper.MessagesMapper;
import com.neusoft.ehr.mapper.ViewMessagesMapper;
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
     * 语言仓库组件
     */
    private final LanguagesMapper languagesMapper;
    /**
     * 国家仓库组件
     */
    private final CountriesMapper countriesMapper;
    /**
     * 消息仓库组件
     */
    private final MessagesMapper messagesMapper;
    /**
     * 视图消息仓库组件
     */
    private final ViewMessagesMapper viewMessagesMapper;

    /**
     * 插入新消息
     *
     * @param po 消息
     */
    @Override
    public void insertMessage(ViewMessagesPo po) {
        LanguagesPo languagesPo = languagesMapper.selectOne(
                Wrappers.<LanguagesPo>lambdaQuery()
                        .eq(LanguagesPo::getName, po.getLanguage())
        );
        if (languagesPo == null) {
            languagesPo = new LanguagesPo();
            languagesPo.setName(po.getLanguage());
            languagesMapper.insert(languagesPo);
        }
        CountriesPo countriesPo = countriesMapper.selectOne(
                Wrappers.<CountriesPo>lambdaQuery()
                        .eq(CountriesPo::getName, po.getCountry())
        );
        if (countriesPo == null) {
            countriesPo = new CountriesPo();
            countriesPo.setName(po.getCountry());
            countriesMapper.insert(countriesPo);
        }
        MessagesPo messagesPo = messagesMapper.selectOne(
                Wrappers.<MessagesPo>lambdaQuery()
                        .eq(MessagesPo::getCode, po.getCode())
                        .eq(MessagesPo::getLanguage, languagesPo.getId())
                        .eq(MessagesPo::getCountry, countriesPo.getId())
        );
        if (messagesPo != null) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        messagesPo = new MessagesPo();
        messagesPo.setCode(po.getCode());
        messagesPo.setLanguage(languagesPo.getId());
        messagesPo.setCountry(countriesPo.getId());
        messagesPo.setText(po.getText());
        messagesMapper.insert(messagesPo);
    }

    /**
     * 删除消息
     *
     * @param po 消息
     */
    @Override
    public void deleteMessage(ViewMessagesPo po) {
        ViewMessagesPo viewMessagesPo = viewMessagesMapper.selectOne(
                Wrappers.<ViewMessagesPo>lambdaQuery()
                        .eq(ViewMessagesPo::getCode, po.getCode())
                        .eq(ViewMessagesPo::getLanguage, po.getLanguage())
                        .eq(ViewMessagesPo::getCountry, po.getCountry())
        );
        if (viewMessagesPo != null) {
            messagesMapper.deleteById(viewMessagesPo.getId());
        }
    }

    @Override
    public void updateMessage(ViewMessagesPo po) {
        ViewMessagesPo viewMessagesPo = viewMessagesMapper.selectOne(
                Wrappers.<ViewMessagesPo>lambdaQuery()
                        .eq(ViewMessagesPo::getCode, po.getCode())
                        .eq(ViewMessagesPo::getLanguage, po.getLanguage())
                        .eq(ViewMessagesPo::getCountry, po.getCountry())
        );
        if (viewMessagesPo != null) {
            MessagesPo messagesPo = messagesMapper.selectById(viewMessagesPo.getId());
            messagesPo.setText(po.getText());
            messagesMapper.updateById(messagesPo);
        }
    }

    @Override
    public IPage<ViewMessagesPo> selectMessage(ViewMessagesPo po, Integer page, Integer size) {
        LambdaQueryWrapper<ViewMessagesPo> wrapper = Wrappers.lambdaQuery();
        if (StringUtils.hasText(po.getCode())) {
            wrapper.eq(ViewMessagesPo::getCode, po.getCode());
        }
        if (StringUtils.hasText(po.getLanguage())) {
            wrapper.eq(ViewMessagesPo::getLanguage, po.getLanguage());
        }
        if (StringUtils.hasText(po.getCountry())) {
            wrapper.eq(ViewMessagesPo::getCountry, po.getCountry());
        }
        return viewMessagesMapper.selectPage(Page.of(page, size), wrapper);
    }

    /**
     * 查询消息
     *
     * @param code 消息码
     * @return 消息
     */
    @Override
    public ViewMessagesPo selectMessage(String code, Locale locale) {
        return viewMessagesMapper.selectOne(
                Wrappers.<ViewMessagesPo>lambdaQuery()
                        .eq(ViewMessagesPo::getCode, code)
                        .eq(ViewMessagesPo::getLanguage, locale.getLanguage())
                        .eq(ViewMessagesPo::getCountry, locale.getCountry())
        );
    }
}
