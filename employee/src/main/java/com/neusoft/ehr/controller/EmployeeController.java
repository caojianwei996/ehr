package com.neusoft.ehr.controller;

import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.entity.po.ViewDepartmentResumePo;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.entity.po.ViewPositionResumePo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.service.IReverseService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * Employee 前端控制器
 * </p>
 *
 * @author 吉兆鹏
 * @since 2025-01-08
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController extends BaseController {
    private final IEmployeeService employeeService;
    private final IReverseService reverseService;

    /**
     * EmployeeController构造方法
     *
     * @param messageSource   国际化组件
     * @param employeeService 员工业务组件
     */
    public EmployeeController(MessageSource messageSource, IEmployeeService employeeService, IReverseService reverseService) {
        super(messageSource);
        this.employeeService = employeeService;
        this.reverseService = reverseService;
    }

    @Authority(2)
    @PostMapping
    public Response<Void> addEmployee(@RequestBody Request<AddEmployeeInfoDto> request) {

        System.out.println(request);
        employeeService.addEmployee(request.getData());
        return success();
    }

    @Authority(2)
    @PutMapping
    public Response<Void> updateEmployee(@RequestBody Request<UpdateEmployeeDto> request) {
        employeeService.updateEmployee(request.getData());
        return success();
    }

    @Authority(0)
    @GetMapping("/basic")
    public Response<ViewEmployeesPo> getBasicInfo() {
        Long id = AuthorizationInterceptor.getCurrentUser().getId();
        return success(employeeService.getBasicInfo(id));
    }

    @Authority(0)
    @GetMapping("/resume/department")
    public Response<List<ViewDepartmentResumePo>> getDepartmentResume() {
        String name = AuthorizationInterceptor.getCurrentUser().getName();
        return success(employeeService.getDepartmentResume(name));
    }

    @Authority(0)
    @GetMapping("/resume/position")
    public Response<List<ViewPositionResumePo>> getPositionResume() {
        String name = AuthorizationInterceptor.getCurrentUser().getName();
        return success(employeeService.getPositionResume(name));
    }

    @Authority(2)
    @PostMapping("/reverse/department")
    public Response<Void> reverseDepartment(@RequestBody Request<ReverseDepartmentDto> request) {
        reverseService.reverseDepartment(request.getData());
        return success();
    }

    @Authority(2)
    @PostMapping("/reverse/position")
    public Response<Void> reversePosition(@RequestBody Request<ReversePositionDto> request) {
        reverseService.reversePosition(request.getData());
        return success();
    }
}
