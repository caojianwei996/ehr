package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.time.LocalDateTime;

/**
 * @author xkf
 */
@Data
@Schema(name = "ViewSupplementMonthDto", description = "补签参数接收")
public class ViewSupplementMonthDto {
    @Schema(description = "出勤id")
    @NotNull
    private Long id;
    @Schema(description = "新出勤时间")
    @NotNull
    @Past
    private LocalDateTime clockIn;
    @Schema(description = "新退勤时间")
    @NotNull
    @Past
    private LocalDateTime clockOut;
    @Schema(description = "缺勤原因")
    @NotBlank
    private String reason;
}
