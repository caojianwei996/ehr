package com.neusoft.ehr.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
public class InsertDepartmentsDto {
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
    @NotBlank
    private Long preparation;
}
