package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(name = "ReversePositionDto", description = "员工调转岗位参数接收")
@Data
public class ReversePositionDto {
    @Schema(description = "员工编号")
    @NotNull
    private Long id;
    @Schema(description = "岗位编号")
    @NotNull
    private Long position;
}
