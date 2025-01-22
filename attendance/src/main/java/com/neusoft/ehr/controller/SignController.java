package com.neusoft.ehr.controller;

import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.service.ISignService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RequestMapping("/sign")
@RestController
public class SignController extends BaseController {
    private final ISignService signService;

    /**
     * SignController构造方法
     *
     * @param messageSource 国际化组件
     */
    public SignController(MessageSource messageSource, ISignService signService) {
        super(messageSource);
        this.signService = signService;
    }

    @Authority(0)
    @GetMapping("/time")
    public Response<LocalDateTime> getTimes() {
        return success(LocalDateTime.now());
    }

    @Authority(0)
    @PostMapping("/on")
    public Response<Void> on(@RequestBody @Valid Request<LocalDateTime> request) {
        signService.on(request.getData());
        return success();
    }

    @Authority(0)
    @PostMapping("/off")
    public Response<Void> off(@RequestBody @Valid Request<LocalDateTime> request) {
        signService.off(request.getData());
        return success();
    }
}
