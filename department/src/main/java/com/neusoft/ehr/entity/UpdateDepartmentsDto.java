package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Schema(name = "UpdateDepartmentsDto", description = "更新部门参数接收")
@Data
public class UpdateDepartmentsDto {
    @NotNull
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private Long preparation;
    @NotBlank
    private String leader;
    @NotNull
    private Byte status;
}
