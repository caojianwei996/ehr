package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.AttendancesPo;
import com.neusoft.ehr.entity.po.ViewAttendancesDayPo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.mapper.AttendancesMapper;
import com.neusoft.ehr.mapper.ViewAttendancesDayMapper;
import com.neusoft.ehr.service.ISignService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class SignService implements ISignService {
    private final AttendancesMapper attendancesMapper;
    private final ViewAttendancesDayMapper viewAttendancesDayMapper;

    @Override
    public void on(LocalDateTime time) {
        if (Duration.between(time, LocalDateTime.now()).getSeconds() > 30 ||
                viewAttendancesDayMapper.exists(
                        Wrappers.<ViewAttendancesDayPo>lambdaQuery().eq(
                                ViewAttendancesDayPo::getName,
                                AuthorizationInterceptor.getCurrentUser().getName()
                        )
                )
        ) {
            throw new ServiceException(ServiceCode.FAILURE);
        }
        AttendancesPo attendancesPo = new AttendancesPo();
        attendancesPo.setEmployee(AuthorizationInterceptor.getCurrentUser().getId());
        attendancesPo.setClockIn(time);
        attendancesMapper.insert(attendancesPo);
    }

    @Override
    public void off(LocalDateTime time) {
        if (Duration.between(time, LocalDateTime.now()).getSeconds() > 30) {
            throw new ServiceException(ServiceCode.FAILURE);
        }
        ViewAttendancesDayPo viewAttendancesDayPo = viewAttendancesDayMapper.selectOne(
                Wrappers.<ViewAttendancesDayPo>lambdaQuery().eq(
                        ViewAttendancesDayPo::getName,
                        AuthorizationInterceptor.getCurrentUser().getName()
                )
        );
        if (viewAttendancesDayPo == null || (viewAttendancesDayPo.getClockOut() != null && Duration.between(viewAttendancesDayPo.getClockOut(), time).toMinutes() < 5)) {
            throw new ServiceException(ServiceCode.FAILURE);
        }
        AttendancesPo attendancesPo = attendancesMapper.selectById(viewAttendancesDayPo.getId());
        attendancesPo.setClockOut(time);
        attendancesMapper.updateById(attendancesPo);
    }
}
