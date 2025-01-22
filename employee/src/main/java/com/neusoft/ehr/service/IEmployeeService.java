package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.LoginDto;
import com.neusoft.ehr.entity.UpdatePasswordDto;
import com.neusoft.ehr.entity.AddEmployeeInfoDto;
import com.neusoft.ehr.entity.UpdateEmployeeDto;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.entity.po.ViewDepartmentResumePo;
import com.neusoft.ehr.entity.po.ViewPositionResumePo;
import com.neusoft.ehr.entity.vo.LoginVo;

import java.util.List;


public interface IEmployeeService {
    LoginVo login(LoginDto logindto);

    void reset(String email);

    void updatePassword(UpdatePasswordDto data, LoginVo loginVo);


    void addEmployee(AddEmployeeInfoDto data);

    void updateEmployee(UpdateEmployeeDto data);

    ViewEmployeesPo getBasicInfo(Long id);

    List<ViewDepartmentResumePo> getDepartmentResume(String name);

    List<ViewPositionResumePo> getPositionResume(String name);
}
