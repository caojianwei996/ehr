package com.netsoft.service.impl;

import com.netsoft.entity.dto.ApplyDto;
import com.netsoft.entity.dto.ViewSupplementMonthDto;
import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.netsoft.service.IAttendanceService;
import com.neusoft.ehr.entity.po.SupplementsPo;
import com.neusoft.ehr.entity.po.ViewAttendancesMonthPo;
import com.neusoft.ehr.entity.po.ViewSupplementsPo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
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

    @Autowired
    private ViewSupplementsMapper viewSupplementsMapper;


    @Override
    public List<ViewAttendancesMonthVo> getAllAbsences() {
        List<ViewAttendancesMonthPo> viewAttendancesMonthPos = viewAttendancesMonthMapper.selectList(null);
        List<ViewAttendancesMonthVo> viewAttendancesMonthVos = new ArrayList<>();
        for (ViewAttendancesMonthPo viewAttendancesMonthPo : viewAttendancesMonthPos) {

            ViewAttendancesMonthVo viewAttendancesMonthVo = new ViewAttendancesMonthVo();
            LocalDateTime clockIn = viewAttendancesMonthPo.getClockIn();
            LocalDateTime clockOut = viewAttendancesMonthPo.getClockOut();
            viewAttendancesMonthVo.setEmployee(viewAttendancesMonthPo.getEmployee());
            viewAttendancesMonthVo.setClockIn(clockIn);
            viewAttendancesMonthVo.setClockOut(clockOut);
            if (clockIn == null || clockOut == null) {
                viewAttendancesMonthVo.setType("旷工");
                viewAttendancesMonthVos.add(viewAttendancesMonthVo);
                continue;
            }
            // 上班时间，早上 8 点
            LocalDateTime START_TIME = LocalDateTime.of(clockIn.getYear(), clockIn.getMonth(), clockIn.getDayOfMonth(), 8, 30);
            // 下班时间，下午 5 点
            LocalDateTime END_TIME = LocalDateTime.of(clockOut.getYear(), clockOut.getMonth(), clockOut.getDayOfMonth(), 17, 30);
            // 判断迟到和早退情况
            if (clockIn.isAfter(START_TIME)) {
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

    @Override
    public List<ViewSupplementsPo> getApplies() {
        return viewSupplementsMapper.selectList(null);
    }

    @Override
    public void updateSupplement(ApplyDto data) {
        for (SupplementsPo supplementsPo : data.getList()) {
            supplementsPo.setStatus((byte) 1);
            supplementsMapper.updateById(supplementsPo);
        }
    }

    @Override
    public List<ViewAttendancesMonthPo> getAttendancesMonth() {
        return viewAttendancesMonthMapper.selectList(null);
    }
}
