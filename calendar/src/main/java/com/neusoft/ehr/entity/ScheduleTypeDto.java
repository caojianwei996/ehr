package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalTime;

@Schema(name = "ScheduleTypeDto", description = "工作类型参数接收")
@Data
public class ScheduleTypeDto {
    @Schema(description = "工作类型名称")
    private String name;
    @Schema(description = "上班时间")
    private LocalTime onTime;
    @Schema(description = "下班时间")
    private LocalTime offTime;
}
