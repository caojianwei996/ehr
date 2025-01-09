package com.netsoft.service;

import com.netsoft.entity.dto.ViewSupplementMonthDto;
import com.netsoft.entity.vo.ViewAttendancesMonthVo;
import com.neusoft.ehr.entity.Request;

import java.util.List;

public interface IAttendanceService {
    List<ViewAttendancesMonthVo> getAllAbsences();

    void addSupplement(ViewSupplementMonthDto request);
}
