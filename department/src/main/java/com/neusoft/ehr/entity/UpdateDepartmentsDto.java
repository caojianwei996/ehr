package com.neusoft.ehr.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class UpdateDepartmentsDto {
    @NotNull
    private Long id;
    private String name;
    private Long preparation;
    private Long leader;
    private Byte status;
}
