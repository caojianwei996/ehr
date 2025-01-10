package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.vo.LoginVo;

public interface IEmployeeService {
    LoginVo login(LoginDto logindto);

    void reset(String email);

    void updatePassword(UpdatePasswordDto data, LoginVo loginVo);
}
