package com.neusoft.ehr.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * @Author xkf
 * @Date 2025/1/8 16:24
 * @DESCRIBTE
 */
@Getter
@Setter
@Schema(name = "ViewSupplementMonthDto", description = "补签参数接收")
public class ViewSupplementMonthDto {

    @Schema(description = "新出勤时间")
    private LocalDateTime clockIn;

    @Schema(description = "新退勤时间")
    private LocalDateTime clockOut;

    @Schema(description = "缺勤原因")
    private String reason;
}
