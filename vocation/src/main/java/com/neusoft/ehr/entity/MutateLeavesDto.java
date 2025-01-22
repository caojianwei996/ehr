package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Schema(name = "AddLeavesDto", description = "修改假期参数接收")
public class MutateLeavesDto {
    @Schema(description = "假期类型编号")
    @NotNull
    private Long id;
    @Schema(description = "假期类型名称")
    @NotBlank
    private String name;
    @Schema(description = "假期受限")
    @NotNull
    private Boolean restricted;
}