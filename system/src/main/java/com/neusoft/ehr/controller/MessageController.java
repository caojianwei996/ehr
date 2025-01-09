package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.ViewMessagesPo;
import com.neusoft.ehr.service.IMessageService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.*;

/**
 * 消息控制器
 *
 * @author 曹健伟
 */
@Schema(name = "MessageController", description = "消息控制器")
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
    public Response<Void> insert(@RequestBody Request<ViewMessagesPo> request) {
        messageService.insertMessage(request.getData());
        return success();
    }

    @DeleteMapping
    public Response<Void> delete(@RequestBody Request<ViewMessagesPo> request) {
        messageService.deleteMessage(request.getData());
        return success();
    }

    @GetMapping
    public Response<IPage<ViewMessagesPo>> select(@RequestParam("page") Integer page, @RequestParam("size") Integer size) {
        return success(messageService.selectMessage(LocaleContextHolder.getLocale(), page, size));
    }

    @GetMapping("/{code}")
    public Response<ViewMessagesPo> select(@PathVariable("code") String code) {
        return success(messageService.selectMessage(code, LocaleContextHolder.getLocale()));
    }
}
