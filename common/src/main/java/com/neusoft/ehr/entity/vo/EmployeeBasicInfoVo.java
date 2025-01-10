package com.neusoft.ehr.entity.vo;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author 吉兆鹏
 * 员工基本信息
 */
@Getter
@Setter
public class EmployeeBasicInfoVo {
    private String name;
    private String email;
    private String department;
    private String position;
    private String sex;
    //初次参加工作时间
    private LocalDate induction;
    private LocalDate birthday;
    private LocalDate start;
}
