package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Schema(name = "InsertDepartmentsDto", description = "新增部门参数接收")
@Data
public class InsertDepartmentsDto {
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
    @NotBlank
    private String leader;
    @NotNull
    private Long preparation;
    @NotBlank
    private String parentName;
}
