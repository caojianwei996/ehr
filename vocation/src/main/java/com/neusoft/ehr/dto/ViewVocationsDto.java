package com.neusoft.ehr.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Schema(description = "申请假期数据")
@Data
public class ViewVocationsDto {
    @Schema(description = "假期类型")
    @NotNull
    private Long type;
    @Schema(description = "开始日期")
    @Future
    private LocalDate start;
    @Schema(description = "结束日期")
    @Future
    private LocalDate end;
}
