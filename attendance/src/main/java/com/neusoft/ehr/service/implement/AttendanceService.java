package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.ViewSupplementMonthDto;
import com.neusoft.ehr.entity.po.*;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.mapper.*;
import com.neusoft.ehr.service.IAttendanceService;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author xkf
 */
@RequiredArgsConstructor
@Service
public class AttendanceService implements IAttendanceService {
    private final ViewAttendancesDayMapper viewAttendancesDayMapper;
    private final ViewAttendancesMonthMapper viewAttendancesMonthMapper;
    private final ViewSupplementsMapper viewSupplementsMapper;
    private final SupplementsMapper supplementsMapper;
    private final AttendancesMapper attendancesMapper;
    private final EmployeesMapper employeesMapper;

    @Override
    public ViewAttendancesDayPo getAttendancesDay() {
        return viewAttendancesDayMapper.selectOne(
                Wrappers.<ViewAttendancesDayPo>lambdaQuery()
                        .eq(ViewAttendancesDayPo::getEmployeeId, AuthorizationInterceptor.getCurrentUser().getId())
        );
    }

    @Override
    public List<ViewAttendancesMonthPo> getAttendancesMonth() {
        return viewAttendancesMonthMapper.selectList(
                Wrappers.<ViewAttendancesMonthPo>lambdaQuery().eq(
                        ViewAttendancesMonthPo::getEmployeeId, AuthorizationInterceptor.getCurrentUser().getId()
                )
        );
    }

    @Override
    public void addSupplement(ViewSupplementMonthDto viewSupplementMonthDto) {
        Long id = AuthorizationInterceptor.getCurrentUser().getId();
        AttendancesPo attendancesPo = attendancesMapper.selectOne(
                Wrappers.<AttendancesPo>lambdaQuery()
                        .eq(AttendancesPo::getEmployee, id)
                        .between(
                                AttendancesPo::getClockIn,
                                viewSupplementMonthDto.getClockIn().with(LocalTime.MIN),
                                viewSupplementMonthDto.getClockIn().with(LocalTime.MAX)
                        )
        );
        if (attendancesPo == null || attendancesPo.getStatus() != 3) {
            throw new ServiceException(ServiceCode.FAILURE);
        }
        SupplementsPo supplementsPo = new SupplementsPo();
        supplementsPo.setEmployee(id);
        supplementsPo.setClockIn(viewSupplementMonthDto.getClockIn());
        supplementsPo.setClockOut(viewSupplementMonthDto.getClockOut());
        supplementsPo.setReason(viewSupplementMonthDto.getReason());
        supplementsMapper.insert(supplementsPo);
        attendancesMapper.update(
                Wrappers.<AttendancesPo>lambdaUpdate()
                        .set(AttendancesPo::getStatus, 4)

        );
    }

    @Override
    public List<ViewSupplementsPo> getApplies() {
        List<Long> collect = employeesMapper.selectList(
                Wrappers.<EmployeesPo>lambdaQuery().eq(
                        EmployeesPo::getLeader,
                        AuthorizationInterceptor.getCurrentUser().getId()
                )
        ).stream().map(EmployeesPo::getId).collect(Collectors.toList());
        return viewSupplementsMapper.selectList(
                Wrappers.<ViewSupplementsPo>lambdaQuery().in(
                        !collect.isEmpty(), ViewSupplementsPo::getEmployeeId, collect
                )
        );
    }

    @Override
    public void updateSupplement(ApplyDto data) {
        SupplementsPo supplementsPo = supplementsMapper.selectById(data.getId());
        if (supplementsPo != null) {
            supplementsPo.setFiltered(true);
            supplementsMapper.updateById(supplementsPo);
            attendancesMapper.update(
                    Wrappers.<AttendancesPo>lambdaUpdate()
                            .set(AttendancesPo::getStatus, data.getAgree() ? 5 : 6)
                            .eq(AttendancesPo::getEmployee, AuthorizationInterceptor.getCurrentUser().getId())
                            .between(
                                    AttendancesPo::getClockIn,
                                    supplementsPo.getClockIn().with(LocalTime.MIN),
                                    supplementsPo.getClockIn().with(LocalTime.MAX)
                            )
            );
        }
    }
}
