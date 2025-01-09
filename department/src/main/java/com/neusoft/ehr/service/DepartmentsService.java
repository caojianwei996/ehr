package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import org.springframework.stereotype.Service;

import java.util.List;

//mybatis-plus:

public interface DepartmentsService {
    void insertDepartments(DepartmentsDTO departmentsDTO);

    DepartmentsPo updateDepartment(DepartmentsDTO departmentsDTO);

    List<DepartmentsPo> pageDepartments(Integer limit, Integer page);
}
