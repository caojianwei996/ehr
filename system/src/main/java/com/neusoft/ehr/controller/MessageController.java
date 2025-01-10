package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.ViewMessagesPo;
import com.neusoft.ehr.service.IMessageService;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 消息控制器
 *
 * @author 曹健伟
 */
@RequestMapping("/messages")
@RestController
public class MessageController extends BaseController {
    /**
     * 消息业务组件
     */
    private final IMessageService messageService;

    /**
     * MessageController构造方法
     *
     * @param messageSource  国际化组件
     * @param messageService 消息业务组件
     */
    public MessageController(MessageSource messageSource, IMessageService messageService) {
        super(messageSource);
        this.messageService = messageService;
    }

    @PostMapping
    public Response<Void> insert(@RequestBody @Valid Request<ViewMessagesPo> request) {
        messageService.insertMessage(request.getData());
        return success();
    }

    @DeleteMapping
    public Response<Void> delete(
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "country", required = false) String country
    ) {
        ViewMessagesPo po = new ViewMessagesPo();
        po.setCode(code);
        po.setLanguage(language);
        po.setCountry(country);
        messageService.deleteMessage(po);
        return success();
    }

    @PutMapping
    public Response<Void> update(@RequestBody @Valid Request<ViewMessagesPo> request) {
        messageService.updateMessage(request.getData());
        return success();
    }

    @GetMapping
    public Response<IPage<ViewMessagesPo>> select(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size,
            @RequestParam(value = "code", required = false) String code,
            @RequestParam(value = "language", required = false) String language,
            @RequestParam(value = "country", required = false) String country
    ) {
        ViewMessagesPo po = new ViewMessagesPo();
        po.setCode(code);
        po.setLanguage(language);
        po.setCountry(country);
        return success(messageService.selectMessage(po, page, size));
    }

    @GetMapping("/{code}")
    public Response<ViewMessagesPo> select(@PathVariable("code") String code) {
        return success(messageService.selectMessage(code, LocaleContextHolder.getLocale()));
    }
}
