package com.neusoft.ehr.entity;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class UpdatePositionsDto {
    @NotNull
    private Long id;

    @NotBlank
    @Size(min = 1, max = 64)
    private String name;

    @NotBlank
    private Byte level;

    @NotBlank
    private Byte status;
}
