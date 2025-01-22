package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.InsertDepartmentsDto;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.UpdateDepartmentsDto;
import com.neusoft.ehr.entity.po.ViewDepartmentsPo;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.service.IDepartmentsService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequestMapping("/departments")
@RestController
public class DepartmentController extends BaseController {
    private final IDepartmentsService departmentsService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public DepartmentController(MessageSource messageSource, IDepartmentsService departmentsService) {
        super(messageSource);
        this.departmentsService = departmentsService;
    }

    //根据部门id查询所有该部门下的员工
    @Authority(1)
    @GetMapping("/employees/{departmentId}")
    public Response<IPage<ViewEmployeesPo>> selectEmployeesByDepartmentId(
            @PathVariable Long departmentId,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return success(departmentsService.selectEmployeesByDepartmentId(departmentId, page, limit));
    }

    //根据部门名称（非必须）以及状态（非必须）查询部门
    @Authority(0)
    @GetMapping("/search")
    public Response<IPage<ViewDepartmentsPo>> selectDepartments(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Byte status,
            @RequestParam(value = "page", defaultValue = "1") Integer page,
            @RequestParam(value = "limit", defaultValue = "10") Integer limit) {
        return success(departmentsService.selectDepartments(name, status, page, limit));
    }

    //根据部门id查询部门详细信息
    @Authority(0)
    @GetMapping("/{id}")
    public Response<ViewDepartmentsPo> selectDepartmentById(@PathVariable Long id) {
        return success(departmentsService.selectDepartmentById(id));
    }

    @Authority(2)
    @PostMapping
    public Response<Void> insertDepartments(@RequestBody @Valid Request<InsertDepartmentsDto> request) {
        departmentsService.insertDepartments(request.getData());
        return success();
    }

    @PutMapping
    public Response<Void> updateDepartment(@RequestBody @Valid Request<UpdateDepartmentsDto> request) {
        departmentsService.updateDepartment(request.getData());
        return success();
    }
}
