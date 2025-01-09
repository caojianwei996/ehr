package com.neusoft.ehr.controller;

import com.neusoft.ehr.dto.DepartmentsDTO;
import com.neusoft.ehr.vo.DepartmentsVO;
import com.neusoft.ehr.entity.Request;
import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.DepartmentsPo;
import com.neusoft.ehr.service.DepartmentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepartmentController extends BaseController {
    @Autowired
    private DepartmentsService departmentsService;

    /**
     * BaseController构造方法
     *
     * @param messageSource 国际化组件
     */
    protected DepartmentController(MessageSource messageSource) {
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
    public Response<DepartmentsVO> updateDepartment(@RequestBody Request<DepartmentsDTO> departmentsDTO) {
        DepartmentsVO updatedDept = departmentsService.updateDepartment(departmentsDTO.getData());
        return success(updatedDept);
    }
}
