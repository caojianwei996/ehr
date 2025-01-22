package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(name = "AddEmployeeInfoDto", description = "新增员工参数接收")
@Data
public class UpdateEmployeeDto {
    @Schema(description = "员工编号")
    @NotNull
    private Long id;
    @Schema(description = "员工邮箱")
    private String email;
    @Schema(description = "员工工资")
    private Long salary;
    @Schema(description = "员工权限")
    private Byte authority;
    @Schema(description = "员工状态")
    private Byte status;
}
