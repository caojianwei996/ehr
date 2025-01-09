package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import org.springframework.stereotype.Service;

//mybatis-plus:

public interface DepartmentsService {
    DepartmentsVO insertDepartments(DepartmentsDTO departmentsDTO);

    DepartmentsVO updateDepartment(DepartmentsDTO departmentsDTO);
}
