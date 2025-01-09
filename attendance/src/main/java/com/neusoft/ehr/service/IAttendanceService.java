package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.dto.ViewSupplementMonthDto;
import com.neusoft.ehr.entity.vo.ViewAttendancesMonthVo;
import com.neusoft.ehr.entity.dto.ApplyDto;
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
