package com.neusoft.ehr.service;

import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import org.springframework.stereotype.Service;

@Service
public interface DepartmentsService {
    DepartmentsVO insertDepartments(DepartmentsDTO departmentsDTO);

    DepartmentsVO updateDepartment(DepartmentsDTO departmentsDTO);
}
