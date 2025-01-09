package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.dto.AddEmployeeInfoDto;
import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.vo.LoginVo;

import javax.validation.constraints.NotNull;

public interface IEmployeeService {
    LoginVo login(LoginDto logindto);

    void reset(String email);

    void updatePassword(UpdatePasswordDto data,LoginVo loginVo);

    void addEmployee(@NotNull AddEmployeeInfoDto data);
}
