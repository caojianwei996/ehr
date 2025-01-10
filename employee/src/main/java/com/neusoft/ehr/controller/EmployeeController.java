package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.AddEmployeeInfoDto;
import com.neusoft.ehr.entity.LoginDto;
import com.neusoft.ehr.entity.UpdateEmployeeDto;
import com.neusoft.ehr.entity.UpdatePasswordDto;
import com.neusoft.ehr.entity.EmployeeBasicInfoVo;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * Employee 前端控制器
 * </p>
 *
 * @author 吉兆鹏
 * @since 2025-01-08
 */
@RestController
@RequestMapping("/users")
public class EmployeeController extends BaseController {
    private final IEmployeeService employeeService;

    /**
     * EmployeeController构造方法
     *
     * @param messageSource   国际化组件
     * @param employeeService 员工业务组件
     */
    public EmployeeController(MessageSource messageSource, IEmployeeService employeeService) {
        super(messageSource);
        this.employeeService = employeeService;
    }

    @PostMapping("/login")
    public Response<LoginVo> login(@RequestBody Request<LoginDto> request) {
        return success(employeeService.login(request.getData()));
    }

    @PostMapping("/reset")
    public Response<Void> resetPassword(@RequestBody Request<String> request) {
        employeeService.reset(request.getData());
        return success();
    }

    @PostMapping("/update")
    public Response<Void> updatePassword(@RequestBody Request<UpdatePasswordDto> request) {
        employeeService.updatePassword(request.getData(), AuthorizationInterceptor.getCurrentUser());
        return success();
    }
    @PostMapping("/employee")
    public Response<Void> addEmployee(@RequestBody Request<AddEmployeeInfoDto> request){

        System.out.println(request);
        employeeService.addEmployee(request.getData());
        return success();
    }

    @PutMapping("/employee")
    public Response<Void> updateEmployee(@RequestBody Request<UpdateEmployeeDto> request){

        employeeService.updateEmployee(request.getData());
        return success();
    }

    @GetMapping("/employees/basic")
    public Response<EmployeeBasicInfoVo> getBasicInfo(){
        LoginVo currentUser = AuthorizationInterceptor.getCurrentUser();

        return null;
    }
}
