package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.*;
import java.time.LocalDate;

@Schema(name = "AddEmployeeInfoDto", description = "新增员工参数接收")
@Data
public class AddEmployeeInfoDto {
    @Schema(description = "姓名")
    @NotBlank
    private String name;
    @Schema(description = "邮箱")
    @NotBlank
    @Email
    private String email;
    @Schema(description = "性别")
    @NotNull
    private Byte gender;
    @Schema(description = "生日")
    @NotBlank
    @Past
    private LocalDate birthday;
    @Schema(description = "入职日期")
    @NotBlank
    @Future
    private LocalDate induction;
    @Schema(description = "工资")
    @NotNull
    private Long salary;
    @Schema(description = "权限级别")
    @NotNull
    private Byte authority;
    @Schema(description = "部门编号")
    @NotNull
    private Long department;
    @Schema(description = "岗位编号")
    @NotNull
    private Long position;
    @Schema(description = "工作类型编号")
    @NotNull
    private Long attendance;
}
