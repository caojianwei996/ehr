package com.neusoft.ehr.controller;

import com.neusoft.ehr.DTO.DepartmentsDTO;
import com.neusoft.ehr.VO.DepartmentsVO;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController extends BaseController {
    @Autowired
    private DepartmentsService departmentsService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    public DeptController(MessageSource messageSource) {
        super(messageSource);
    }

    @GetMapping("/departments")
    public Response<List<DepartmentsPo>> getDepartments(@RequestParam(required = false) Integer limit,
                                                        @RequestParam(required = false) Integer page) {
        List<DepartmentsPo> departmentsList = departmentsService.pageDepartments(limit, page);
        return success(departmentsList);
    }

    @PostMapping("/departments")
    public Response<Void> addDepartments(@RequestBody Request<DepartmentsDTO> departmentsDTO) {
        departmentsService.insertDepartments(departmentsDTO.getData());
        return success();
    }

    @PutMapping("/departments")
    public Response<DepartmentsPo> updateDepartment(@RequestBody Request<DepartmentsDTO> departmentsDTO) {
        DepartmentsPo updatedDept = departmentsService.updateDepartment(departmentsDTO.getData());
        return success(updatedDept);
    }
}
