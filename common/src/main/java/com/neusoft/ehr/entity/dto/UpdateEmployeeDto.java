package com.neusoft.ehr.entity.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class UpdateEmployeeDto {
    private Long id;
    private String name;
    private String email;
    private LocalDate birthday;
    private Long salary;
    private Byte authority;
    private Long department;
    private Long position;
    private Long attendance;
    private Byte status;
}
