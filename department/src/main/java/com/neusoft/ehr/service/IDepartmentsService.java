package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.InsertDepartmentsDto;
import com.neusoft.ehr.entity.UpdateDepartmentsDto;
import com.neusoft.ehr.entity.po.ViewDepartmentsPo;

public interface IDepartmentsService {
    IPage<ViewDepartmentsPo> selectDepartments(Integer page, Integer limit);

    void insertDepartments(InsertDepartmentsDto departmentsDTO);

    void updateDepartment(UpdateDepartmentsDto departmentsDTO);
}
