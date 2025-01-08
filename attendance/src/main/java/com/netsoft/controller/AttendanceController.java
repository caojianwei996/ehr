package com.netsoft.controller;

import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.neusoft.ehr.controller.BaseController;
import com.neusoft.ehr.entity.Request;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author xkf
 * @Date 2025/1/8 15:55
 * @DESCRIBTE
 */
@RestController
public class AttendanceController extends BaseController {
    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected AttendanceController(MessageSource messageSource) {
        super(messageSource);
    }

    @GetMapping("/attendances")
    public Request<List<ViewAttendancesMonthVo>> getAllAbsences() {

        return null;
    }
}
