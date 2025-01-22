package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.LeavesPo;
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

    @Authority(0)
    @GetMapping("/types/{id}")
    public Response<LeavesPo> getVocationType(@PathVariable("id") Long id) {
        return success(vocationService.getVocationType(id));
    }

    @Authority(0)
    @GetMapping("/types")
    public Response<List<LeavesPo>> getVocationTypes() {
        return success(vocationService.getVocationTypes());
    }

    @Authority(2)
    @PostMapping("/types")
    public Response<Void> addVocationTypes(@RequestBody @Valid Request<AddLeavesDto> request) {
        vocationService.addVocationTypes(request.getData());
        return success();
    }

    @Authority(2)
    @PutMapping("/types")
    public Response<Void> mutateVocationTypes(@RequestBody @Valid Request<MutateLeavesDto> request) {
        vocationService.mutateVocationTypes(request.getData());
        return success();
    }

    @Authority(2)
    @DeleteMapping("/types")
    public Response<Void> removeVocationTypes(@RequestParam("id") Long id) {
        vocationService.removeVocationTypes(id);
        return success();
    }

    @Authority(0)
    @GetMapping("/last")
    public Response<Short> getLast() {
        return success(vocationService.getLast());
    }

    @Authority(0)
    @GetMapping
    public Response<IPage<ViewVocationsPo>> getVocations(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return success(vocationService.getVocations(page, size));
    }

    @Authority(0)
    @PostMapping("/apply")
    public Response<Void> applyVocation(@RequestBody @Valid Request<ViewVocationsDto> request) {
        vocationService.applyVocation(request.getData());
        return success();
    }

    @Authority(1)
    @GetMapping("/apply")
    public Response<List<ViewVocationsPo>> getApply() {
        return success(vocationService.getApply());
    }

    @Authority(1)
    @PutMapping("/apply")
    public Response<Void> updateApply(@RequestBody @Valid Request<ApplyDto> request) {
        vocationService.updateApply(request.getData());
        return success();
    }
}
