package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.*;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.entity.po.ViewResumeDepartmentVo;
import com.neusoft.ehr.entity.po.ViewResumePositionVo;
import com.neusoft.ehr.entity.vo.LoginVo;
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
    public EmployeeController(MessageSource messageSource, IEmployeeService employeeService,IReverseService reverseService) {
        super(messageSource);
        this.employeeService = employeeService;
        this.reverseService = reverseService;
    }


    @PostMapping
    public Response<Void> addEmployee(@RequestBody Request<AddEmployeeInfoDto> request){

        System.out.println(request);
        employeeService.addEmployee(request.getData());
        return success();
    }

    @PutMapping
    public Response<Void> updateEmployee(@RequestBody Request<UpdateEmployeeDto> request){
        employeeService.updateEmployee(request.getData());
        return success();
    }

    @GetMapping("/basic")
    public Response<ViewEmployeesPo> getBasicInfo(){
        Long id = AuthorizationInterceptor.getCurrentUser().getId();
        return success(employeeService.getBasicInfo(id));
    }
    @GetMapping("/resume/department")
    public Response<List<ViewResumeDepartmentVo>> getDepartmentResume(){
        String name = AuthorizationInterceptor.getCurrentUser().getName();
        return success(employeeService.getDepartmentResume(name));
    }
    @GetMapping("/resume/position")
    public Response<List<ViewResumePositionVo>> getPositionResume(){
        String name = AuthorizationInterceptor.getCurrentUser().getName();
        return success(employeeService.getPositionResume(name));
    }
    @PostMapping("/reverse/department")
    public Response<Void> reverseDepartment(@RequestBody Request<ReverseDepartmentDto> request){
        reverseService.reverseDepartment(request.getData());
        return success();
    }
    @PostMapping("/reverse/position")
    public Response<Void> reversePosition(@RequestBody Request<ReversePositionDto> request){
        reverseService.reversePosition(request.getData());
        return success();
    }

}
