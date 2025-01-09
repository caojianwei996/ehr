package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.vo.LoginVo;

public interface IEmployeeService {
    public LoginVo login(LoginDto logindto);

    Void reset(String email);

    LoginVo updatePassword(UpdatePasswordDto data,LoginVo loginVo);
}
