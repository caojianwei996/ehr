package com.neusoft.ehr.entity;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddEmployeeInfoDto {
    private String name;
    private String email;
    private Long gender;
    private LocalDate birthday;
    private LocalDate induction;
    private Long salary;
    private Byte authority;
    private Long department;
    private Long position;
    private Long attendance;
}
