package com.neusoft.ehr.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.exceptions.NameConflictException;
import com.neusoft.ehr.mapper.DepartmentsMapper;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Override
    public DepartmentsVO insertDepartments(DepartmentsDTO departmentsDTO) {
        // 检查部门名称是否已存在
        QueryWrapper<DepartmentsPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", departmentsDTO.getName());
        if (departmentsMapper.selectCount(queryWrapper) > 0) {
            throw new NameConflictException("部门名称已存在");
        }

        DepartmentsPo departmentsPo = new DepartmentsPo();
        /*BeanUtils.copyProperties(departmentsDTO, departmentsPo);
        departmentsPo.setLeader(0L);
        departmentsPo.setStatus((byte) 1);*/

        departmentsMapper.insert(departmentsPo);

        DepartmentsVO departmentsVo = new DepartmentsVO();
        BeanUtils.copyProperties(departmentsPo, departmentsVo);

        return departmentsVo;
    }

    @Override
    public DepartmentsVO updateDepartment(DepartmentsDTO departmentsDTO) {
        return null;
    }
}
