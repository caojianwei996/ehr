package com.neusoft.ehr.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmployeeInfoVo {
    private String name;
    private String email;
    private String gender;
    private LocalDateTime birthday;
    private Long salary;
    private Byte authority;
    private String department;
    private String position;
    private String attendance;
}
