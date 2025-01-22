package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.ScheduleTypeDto;
import com.neusoft.ehr.entity.po.WorkTypesPo;
import com.neusoft.ehr.service.IScheduleTypeService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/schedules")
@RestController
public class ScheduleTypeController extends BaseController {
    private final IScheduleTypeService scheduleTypeService;

    /**
     * ScheduleTypeController构造方法
     *
     * @param messageSource 国际化组件
     */
    public ScheduleTypeController(MessageSource messageSource, IScheduleTypeService scheduleTypeService) {
        super(messageSource);
        this.scheduleTypeService = scheduleTypeService;
    }

    @Authority(2)
    @GetMapping
    public Response<IPage<WorkTypesPo>> getScheduleTypes(
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "size", defaultValue = "10") Integer size
    ) {
        return success(scheduleTypeService.getScheduleTypes(page, size));
    }

    @Authority(2)
    @PostMapping
    public Response<Void> addScheduleTypes(@RequestBody @Valid Request<ScheduleTypeDto> request) {
        scheduleTypeService.addScheduleTypes(request.getData());
        return success();
    }

    @Authority(2)
    @DeleteMapping("/{id}")
    public Response<Void> deleteScheduleTypes(@PathVariable("id") Long id) {
        scheduleTypeService.deleteScheduleTypes(id);
        return success();
    }
}
