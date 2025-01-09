package com.netsoft.service;

import com.netsoft.entity.dto.ApplyDto;
import com.netsoft.entity.dto.ViewSupplementMonthDto;
import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.po.ViewAttendancesMonthPo;
import com.neusoft.ehr.entity.po.ViewSupplementsPo;

import java.util.List;

public interface IAttendanceService {
    List<ViewAttendancesMonthVo> getAllAbsences();

    void addSupplement(ViewSupplementMonthDto request);

    List<ViewSupplementsPo> getApplies();

    void updateSupplement(ApplyDto data);

    List<ViewAttendancesMonthPo> getAttendancesMonth();
}
