package com.netsoft.controller;

import com.netsoft.entity.dto.ViewSupplementMonthDto;
import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.netsoft.service.IAttendanceService;
import com.neusoft.ehr.controller.BaseController;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.ViewSupplementsPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author xkf
 * @Date 2025/1/8 15:55
 * @DESCRIBTE
 */
@RestController
public class AttendanceController extends BaseController {
    @Autowired
    private IAttendanceService attendanceService;
    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected AttendanceController(MessageSource messageSource) {
        super(messageSource);
    }

    @GetMapping("/attendances")
    public Response<List<ViewAttendancesMonthVo>> getAllAbsences() {
        List<ViewAttendancesMonthVo> allAbsences = attendanceService.getAllAbsences();
        return success(allAbsences);
    }

    @PostMapping("/attendance")
    public Response addSupplement(Request<ViewSupplementMonthDto> request) {
        attendanceService.addSupplement(request.getData());
        return success();
    }

    @GetMapping("/attendances/apply")
    public Response<List<ViewSupplementsPo>>
}
