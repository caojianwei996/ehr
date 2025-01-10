package com.neusoft.ehr.scheduling;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.AttendancesPo;
import com.neusoft.ehr.mapper.AttendancesMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@RequiredArgsConstructor
@Component
public class AttendanceScheduling {
    private final AttendancesMapper attendancesMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void schedule() {
        List<AttendancesPo> attendancesPos = attendancesMapper.selectList(
                Wrappers.<AttendancesPo>lambdaQuery().between(
                        AttendancesPo::getClockIn,
                        LocalDate.now().atStartOfDay().minusDays(1),
                        LocalDate.now().atStartOfDay().minusNanos(1)
                )
        );
        for (AttendancesPo attendancesPo : attendancesPos) {
            if (attendancesPo.getClockIn() != null && attendancesPo.getClockOut() != null) {
                attendancesPo.setStatus((byte) 1);
            } else {
                attendancesPo.setStatus((byte) 2);
            }
        }
        attendancesMapper.updateById(attendancesPos);
    }
}
