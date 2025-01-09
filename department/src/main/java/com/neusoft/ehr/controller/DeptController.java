package com.neusoft.ehr.controller;

import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.exceptions.NameConflictException;
import com.neusoft.ehr.mapper.DepartmentsMapper;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

@RestController
public class DeptController extends BaseController {
    @Autowired
    private DepartmentsService departmentsService;

    private final DepartmentsMapper departmentsMapper;
    protected DeptController(MessageSource messageSource, DepartmentsMapper departmentsMapper) {
        super(messageSource);
        this.departmentsMapper = departmentsMapper;
    }

    @GetMapping("/departments")
    public Response<List<DepartmentsPo>> getDepartments(@RequestParam(required = false) Integer limit,
                                                        @RequestParam(required = false) Integer page) {
        Page<DepartmentsPo> departmentsPage = null;
        if (limit!= null && page!= null) {
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
        return success(departmentsList);
    }

    @PostMapping("/departments")
    public Response addDepartments(@RequestBody DepartmentsDTO departmentsDTO) {
        try {
            departmentsService.insertDepartments(departmentsDTO);
            return success();
        } catch (NameConflictException e) {
            return failure(ServiceCode.NAME_CONFLICT);
        }
    }

    //修改部门信息
    @PutMapping("/departments")
    public Response<DepartmentsVO> updateDepartment(@RequestBody DepartmentsDTO departmentsDTO) {
        DepartmentsVO updatedDept = departmentsService.updateDepartment(departmentsDTO);
        return success(updatedDept);
    }
}
