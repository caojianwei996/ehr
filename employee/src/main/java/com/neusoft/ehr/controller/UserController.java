package com.neusoft.ehr.controller;

import com.neusoft.ehr.aop.authority.Authority;
import com.neusoft.ehr.entity.LoginDto;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.UpdatePasswordDto;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.service.IEmployeeService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * UserController 前端控制器
 * </p>
 *
 * @author 吉兆鹏
 * @since 2025-01-10
 */
@RestController
@RequestMapping("/users")
public class UserController extends BaseController {
    private final IEmployeeService employeeService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public UserController(MessageSource messageSource, IEmployeeService employeeService) {
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

    @Authority(0)
    @PostMapping("/update")
    public Response<Void> updatePassword(@RequestBody Request<UpdatePasswordDto> request) {
        employeeService.updatePassword(request.getData(), AuthorizationInterceptor.getCurrentUser());
        return success();
    }
}
