package com.netsoft.service.impl;

import com.netsoft.entity.dto.ViewSupplementMonthDto;
import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.netsoft.service.IAttendanceService;
import com.neusoft.ehr.entity.po.SupplementsPo;
import com.neusoft.ehr.entity.po.ViewAttendancesMonthPo;
import com.neusoft.ehr.entity.po.ViewSupplementsPo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.interceptor.auth.AuthorizationInterceptor;
import com.neusoft.ehr.mapper.SupplementsMapper;
import com.neusoft.ehr.mapper.ViewAttendancesMonthMapper;
import com.neusoft.ehr.mapper.ViewSupplementsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author xkf
 * @Date 2025/1/8 16:41
 * @DESCRIBTE
 */
@Service
public class AttendanceService implements IAttendanceService {
    @Autowired
    private ViewAttendancesMonthMapper viewAttendancesMonthMapper;

    @Autowired
    private SupplementsMapper supplementsMapper;

    // 上班时间，早上 8 点
    private static final LocalDateTime START_TIME = LocalDateTime.of(2025, 1, 1, 8, 30);
    // 下班时间，下午 5 点
    private static final LocalDateTime END_TIME = LocalDateTime.of(2025, 1, 1, 17, 30);

    @Override
    public List<ViewAttendancesMonthVo> getAllAbsences() {
        List<ViewAttendancesMonthPo> viewAttendancesMonthPos = viewAttendancesMonthMapper.selectList(null);
        List<ViewAttendancesMonthVo> viewAttendancesMonthVos = new ArrayList<>();
        for (ViewAttendancesMonthPo viewAttendancesMonthPo : viewAttendancesMonthPos) {
            ViewAttendancesMonthVo viewAttendancesMonthVo = new ViewAttendancesMonthVo();
            LocalDateTime clockIn = viewAttendancesMonthPo.getClockIn();
            LocalDateTime clockOut = viewAttendancesMonthPo.getClockOut();
            viewAttendancesMonthVo.setClockIn(clockIn);
            viewAttendancesMonthVo.setClockOut(clockOut);
            viewAttendancesMonthVo.setEmployee(viewAttendancesMonthPo.getEmployee());
            if (clockIn == null && clockOut == null) {
                viewAttendancesMonthVo.setType("旷工");
            } else if (clockIn == null || clockIn.isAfter(END_TIME)) {
                viewAttendancesMonthVo.setType("旷工");
            } else if (clockOut == null || clockOut.isBefore(START_TIME)) {
                viewAttendancesMonthVo.setType("旷工");
            } else if (clockIn.isAfter(START_TIME)) {
                viewAttendancesMonthVo.setType("迟到");
            } else if (clockOut.isBefore(END_TIME)) {
                viewAttendancesMonthVo.setType("早退");
            } else {
                continue;
            }
            viewAttendancesMonthVos.add(viewAttendancesMonthVo);
        }

        return viewAttendancesMonthVos;
    }

    @Override
    public void addSupplement(ViewSupplementMonthDto request) {
        SupplementsPo supplementsPo = new SupplementsPo();
        LoginVo loginVo = AuthorizationInterceptor.getCurrentUser();
        supplementsPo.setEmployee(loginVo.getId());
        supplementsPo.setClockIn(request.getClockIn());
        supplementsPo.setClockOut(request.getClockOut());
        supplementsPo.setReason(request.getReason());
        supplementsPo.setStatus((byte) 0);
        supplementsMapper.insert(supplementsPo);
    }
}
