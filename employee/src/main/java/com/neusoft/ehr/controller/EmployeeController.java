package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.dto.LoginDto;
import com.neusoft.ehr.entity.dto.UpdatePasswordDto;
import com.neusoft.ehr.entity.vo.LoginVo;
import com.neusoft.ehr.service.IEmployeeService;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import org.springframework.beans.factory.annotation.Autowired;
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
public class EmployeeController extends BaseController{


    @Autowired
    private IEmployeeService employeeService;
    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public EmployeeController(MessageSource messageSource) {
        super(messageSource);
    }
    @PostMapping("/login")
    public Response<LoginVo> login(@RequestBody Request<LoginDto> request){
        LoginDto data = request.getData();
        return success(employeeService.login(data));
    }
    @PostMapping("/reset")
    public Response<Void> resetPassword(@RequestBody Request<String> request){
        String email = request.getData();

        return success(employeeService.reset(email));
    }
    @PutMapping("/")
    public Response<LoginVo> updatePassword(@RequestBody Request<UpdatePasswordDto> request){
        UpdatePasswordDto data = request.getData();

        return success(employeeService.updatePassword(data));
    }

}
