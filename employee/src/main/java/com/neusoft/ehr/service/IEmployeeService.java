package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.vo.LoginVo;

public interface IEmployeeService {
    public LoginVo login(LoginDto logindto);
}
