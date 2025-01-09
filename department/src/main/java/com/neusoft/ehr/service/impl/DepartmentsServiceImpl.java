package com.neusoft.ehr.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.InsertDepartmentsDto;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.UpdateDepartmentsDto;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.entity.po.ViewDepartmentsPo;
import com.neusoft.ehr.mapper.DepartmentsMapper;
import com.neusoft.ehr.mapper.ViewDepartmentsMapper;
import com.neusoft.ehr.service.DepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentsServiceImpl implements DepartmentsService {
    private final DepartmentsMapper departmentsMapper;
    private final ViewDepartmentsMapper viewDepartmentsMapper;

    @Override
    public IPage<ViewDepartmentsPo> selectDepartments(Integer page, Integer limit) {
        return viewDepartmentsMapper.selectPage(Page.of(page, limit), Wrappers.emptyWrapper());
    }

    @Override
    public void insertDepartments(InsertDepartmentsDto departmentsDto) {
        // 检查部门名称是否已存在
        if (departmentsMapper
                .exists(
                        Wrappers.<DepartmentsPo>lambdaQuery().eq(
                                DepartmentsPo::getName, departmentsDto.getName()
                        )
                )
        ) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        DepartmentsPo departmentsPo = new DepartmentsPo();
        BeanUtils.copyProperties(departmentsDto, departmentsPo);
        departmentsMapper.insert(departmentsPo);
    }

    @Override
    public void updateDepartment(UpdateDepartmentsDto departmentsDto) {
        // 先根据id获取原部门名称
        if (
                departmentsMapper.exists(
                        Wrappers.<DepartmentsPo>lambdaQuery().eq(
                                DepartmentsPo::getName, departmentsDto.getName()
                        )
                )
        ) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        DepartmentsPo departmentsPo = departmentsMapper.selectById(departmentsDto.getId());
        if (departmentsPo == null) {
            return;
        }
        if (departmentsDto.getName() != null) {
            departmentsPo.setName(departmentsDto.getName());
        }
        if (departmentsDto.getPreparation() != null) {
            departmentsPo.setPreparation(departmentsDto.getPreparation());
        }
        if (departmentsDto.getLeader() != null) {
            departmentsPo.setLeader(departmentsDto.getLeader());
        }
        if (departmentsDto.getStatus() != null) {
            departmentsPo.setStatus(departmentsDto.getStatus());
        }
        departmentsMapper.updateById(departmentsPo);
    }
}
