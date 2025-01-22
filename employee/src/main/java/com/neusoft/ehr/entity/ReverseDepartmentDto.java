package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(name = "ReverseDepartmentDto", description = "员工调转部门参数接收")
@Data
public class ReverseDepartmentDto {
    @Schema(description = "员工编号")
    @NotNull
    private Long id;
    @Schema(description = "部门编号")
    @NotNull
    private Long department;
}
