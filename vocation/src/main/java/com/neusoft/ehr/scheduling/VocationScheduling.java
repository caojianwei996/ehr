package com.neusoft.ehr.scheduling;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.VocationsPo;
import com.neusoft.ehr.mapper.VocationsMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@RequiredArgsConstructor
@Component
public class VocationScheduling {
    private final VocationsMapper vocationsMapper;

    @Scheduled(cron = "0 0 0 * * ?")
    public void schedule() {
        vocationsMapper.update(
                Wrappers.<VocationsPo>lambdaUpdate()
                        .eq(VocationsPo::getStart, LocalDate.now())
                        .set(VocationsPo::getStatus, 3)
        );
        vocationsMapper.update(
                Wrappers.<VocationsPo>lambdaUpdate()
                        .eq(VocationsPo::getEnd, LocalDate.now().minusDays(1))
                        .set(VocationsPo::getStatus, 4)
        );
    }
}
