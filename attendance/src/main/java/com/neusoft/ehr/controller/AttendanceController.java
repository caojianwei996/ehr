package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.ViewSupplementMonthDto;
import com.neusoft.ehr.entity.po.ViewAttendancesDayPo;
import com.neusoft.ehr.entity.po.ViewAttendancesMonthPo;
import com.neusoft.ehr.entity.po.ViewSupplementsPo;
import com.neusoft.ehr.service.IAttendanceService;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author xkf
 */
@RequestMapping("/attendances")
@RestController
public class AttendanceController extends BaseController {
    private final IAttendanceService attendanceService;

    /**
     * AttendanceController构造方法
     *
     * @param messageSource 国际化组件
     */
    public AttendanceController(MessageSource messageSource, IAttendanceService attendanceService) {
        super(messageSource);
        this.attendanceService = attendanceService;
    }

    @GetMapping("/day")
    public Response<ViewAttendancesDayPo> getAttendancesDay() {
        return success(attendanceService.getAttendancesDay());
    }

    @GetMapping("/month")
    public Response<List<ViewAttendancesMonthPo>> getAttendancesMonth() {
        return success(attendanceService.getAttendancesMonth());
    }

    @PostMapping("/apply")
    public Response<Void> addSupplement(@RequestBody @Valid Request<ViewSupplementMonthDto> request) {
        attendanceService.addSupplement(request.getData());
        return success();
    }

    @GetMapping("/apply")
    public Response<List<ViewSupplementsPo>> getApplies() {
        List<ViewSupplementsPo> applies = attendanceService.getApplies();
        return success(applies);
    }

    @PutMapping("/apply")
    public Response<Void> updateSupplement(@RequestBody @Valid Request<ApplyDto> request) {
        attendanceService.updateSupplement(request.getData());
        return success();
    }
}
