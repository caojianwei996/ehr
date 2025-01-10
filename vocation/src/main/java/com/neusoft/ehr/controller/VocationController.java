package com.neusoft.ehr.controller;

import com.neusoft.ehr.dto.ViewVocationsDto;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.ViewVocationsPo;
import com.neusoft.ehr.service.IVocationService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/vocations")
@RestController
public class VocationController extends BaseController {
    private final IVocationService vocationService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public VocationController(MessageSource messageSource, IVocationService vocationService) {
        super(messageSource);
        this.vocationService = vocationService;
    }

    @GetMapping
    public Response<Short> getVocations() {
        return success(vocationService.getVocations());
    }

    @PostMapping("/apply")
    public Response<Void> applyVocation(@RequestBody @Valid Request<ViewVocationsDto> request) {
        vocationService.applyVocation(request.getData());
        return success();
    }

    @GetMapping("/apply")
    public Response<List<ViewVocationsPo>> getApply() {
        return success(vocationService.getApply());
    }

    @PutMapping("/apply")
    public Response<Void> updateApply(@RequestBody @Valid Request<ApplyDto> request) {
        vocationService.updateApply(request.getData());
        return success();
    }
}
