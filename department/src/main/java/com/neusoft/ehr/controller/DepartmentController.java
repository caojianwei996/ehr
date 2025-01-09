package com.neusoft.ehr.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.InsertDepartmentsDto;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.UpdateDepartmentsDto;
import com.neusoft.ehr.entity.po.ViewDepartmentsPo;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class DepartmentController extends BaseController {
    private final DepartmentsService departmentsService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected DepartmentController(MessageSource messageSource, DepartmentsService departmentsService) {
        super(messageSource);
        this.departmentsService = departmentsService;
    }

    @GetMapping("/departments")
    public Response<IPage<ViewDepartmentsPo>> selectDepartments(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "10") Integer limit) {
        return success(departmentsService.selectDepartments(page, limit));
    }

    @PostMapping("/departments")
    public Response<Void> insertDepartments(@RequestBody @Valid Request<InsertDepartmentsDto> request) {
        departmentsService.insertDepartments(request.getData());
        return success();
    }

    @PutMapping("/departments")
    public Response<Void> updateDepartment(@RequestBody @Valid Request<UpdateDepartmentsDto> request) {
        departmentsService.updateDepartment(request.getData());
        return success();
    }
}
