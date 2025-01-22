package com.neusoft.ehr.scheduling;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.AttendancesPo;
import com.neusoft.ehr.mapper.AttendancesMapper;
import com.neusoft.ehr.mapper.EmployeesMapper;
import com.neusoft.ehr.util.email.EmailUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Component
public class AttendanceScheduling {
    private final Queue<AttendancesPo> queue = new ConcurrentLinkedQueue<>();
    private final AttendancesMapper attendancesMapper;
    private final EmployeesMapper employeesMapper;
    private final EmailUtil emailUtil;

    @Scheduled(cron = "0 0 0 * * ?")
    public void countAttendances() {
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
                queue.add(attendancesPo);
                attendancesPo.setStatus((byte) 2);
            }
        }
        attendancesMapper.updateById(attendancesPos);
    }

    @Scheduled(cron = "0 0 8 * * ?")
    public void notifyAttendances() {
        List<SimpleMailMessage> messages = new ArrayList<>();
        employeesMapper.selectByIds(
                queue.stream().map(AttendancesPo::getEmployee).collect(Collectors.toList())
        ).forEach(employeesPo -> messages.add(
                emailUtil.getSimpleMailMessage(
                        employeesPo.getEmail(),
                        "缺勤通知",
                        employeesPo.getName() + "您昨日存在缺勤情况，请及时补签，以免影响您的工作")
        ));
        emailUtil.send(messages.toArray(new SimpleMailMessage[0]));
    }
}
