package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.InsertDepartmentsDto;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.UpdateDepartmentsDto;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.entity.po.ViewDepartmentsPo;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.mapper.DepartmentsMapper;
import com.neusoft.ehr.mapper.ViewDepartmentsMapper;
import com.neusoft.ehr.mapper.ViewEmployeesMapper;
import com.neusoft.ehr.service.IDepartmentsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class DepartmentsService implements IDepartmentsService {
    private final DepartmentsMapper departmentsMapper;
    private final ViewDepartmentsMapper viewDepartmentsMapper;
    private final ViewEmployeesMapper viewEmployeesPoMapper;

    @Override
    public IPage<ViewDepartmentsPo> selectDepartments(Integer page, Integer limit) {
        return viewDepartmentsMapper.selectPage(Page.of(page, limit), Wrappers.emptyWrapper());
    }

    @Override
    public IPage<ViewEmployeesPo> selectEmployeesByDepartmentId(Long departmentId, Integer page, Integer limit) {
        return viewEmployeesPoMapper.selectPage(
                Page.of(page, limit),
                Wrappers.<ViewEmployeesPo>lambdaQuery().eq(ViewEmployeesPo::getDepartment_id, departmentId)
        );
    }

    @Override
    public void insertDepartments(InsertDepartmentsDto departmentsDto) {
        if (departmentsMapper.exists(Wrappers.<DepartmentsPo>lambdaQuery().eq(DepartmentsPo::getName, departmentsDto.getName()))) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        DepartmentsPo departmentsPo = new DepartmentsPo();
        BeanUtils.copyProperties(departmentsDto, departmentsPo);
        departmentsMapper.insert(departmentsPo);
    }

    @Override
    public void updateDepartment(UpdateDepartmentsDto departmentsDto) {
        if (departmentsMapper.exists(Wrappers.<DepartmentsPo>lambdaQuery().eq(DepartmentsPo::getName, departmentsDto.getName()))) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }
        departmentsMapper.update(
                Wrappers.<DepartmentsPo>lambdaUpdate()
                        .eq(DepartmentsPo::getId, departmentsDto.getId())
                        .set(departmentsDto.getName() != null, DepartmentsPo::getName, departmentsDto.getName())
                        .set(departmentsDto.getPreparation() != null, DepartmentsPo::getPreparation, departmentsDto.getPreparation())
                        .set(departmentsDto.getLeader() != null, DepartmentsPo::getLeader, departmentsDto.getLeader())
                        .set(departmentsDto.getStatus() != null, DepartmentsPo::getStatus, departmentsDto.getStatus())
        );
    }

    @Override
    public ViewDepartmentsPo selectDepartmentById(Long departmentId) {
        return viewDepartmentsMapper.selectOne(
                Wrappers.<ViewDepartmentsPo>lambdaQuery().eq(ViewDepartmentsPo::getDepartmentId, departmentId));
    }
}
