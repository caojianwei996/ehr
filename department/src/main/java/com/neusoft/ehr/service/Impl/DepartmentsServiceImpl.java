package com.neusoft.ehr.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.mapper.DepartmentsMapper;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DepartmentsServiceImpl implements DepartmentsService {

    @Autowired
    private DepartmentsMapper departmentsMapper;

    @Override
    public void insertDepartments(DepartmentsDTO departmentsDTO) {
        // 检查部门名称是否已存在
        QueryWrapper<DepartmentsPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", departmentsDTO.getName());
        if (departmentsMapper.selectCount(queryWrapper) > 0) {
            throw new ServiceException(ServiceCode.NAME_CONFLICT);
        }

        DepartmentsPo departmentsPo = new DepartmentsPo();
        BeanUtils.copyProperties(departmentsDTO, departmentsPo);

        departmentsMapper.insert(departmentsPo);
    }

    @Override
    public DepartmentsPo updateDepartment(DepartmentsDTO departmentsDTO) {
        // 先根据id获取原部门名称
        DepartmentsPo originalDept = departmentsMapper.selectById(departmentsDTO.getId());

        // 检查新名称是否与其他部门冲突（除了自身外）
        if (!originalDept.getName().equals(departmentsDTO.getName())) {
            QueryWrapper<DepartmentsPo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", departmentsDTO.getName());
            if (departmentsMapper.selectCount(queryWrapper) > 0) {
                throw new ServiceException(ServiceCode.NAME_CONFLICT);
            }
        }

        DepartmentsPo departmentsPo = new DepartmentsPo();
        BeanUtils.copyProperties(departmentsDTO, departmentsPo);
        departmentsMapper.updateById(departmentsPo);

        return departmentsPo;
    }

    @Override
    public List<DepartmentsPo> pageDepartments(Integer limit, Integer page) {
        Page<DepartmentsPo> departmentsPage = null;
        if (limit != null && page != null) {
            Page<DepartmentsPo> pageParam = new Page<>(page, limit);
            QueryWrapper<DepartmentsPo> queryWrapper = new QueryWrapper<>();
            departmentsPage = departmentsMapper.selectPage(pageParam, queryWrapper);
        } else {
            // 如果没有分页参数，查询全部部门信息
            departmentsPage = new Page<>();
            List<DepartmentsPo> allDepartments = departmentsMapper.selectList(null);
            departmentsPage.setRecords(allDepartments);
            departmentsPage.setTotal(allDepartments.size());
        }
        List<DepartmentsPo> departmentsList = departmentsPage.getRecords();
        return departmentsList;
    }
}
