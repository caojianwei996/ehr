package com.netsoft.service;

import com.netsoft.entity.vo.ViewAttendancesMonthVo;

import java.util.List;

public interface IAttendanceService {
    List<ViewAttendancesMonthVo> getAllAbsences();
}
