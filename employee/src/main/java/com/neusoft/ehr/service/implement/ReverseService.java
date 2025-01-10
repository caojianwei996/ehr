package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.neusoft.ehr.entity.ReverseDepartmentDto;
import com.neusoft.ehr.entity.ReversePositionDto;
import com.neusoft.ehr.entity.po.DepartmentResumePo;
import com.neusoft.ehr.entity.po.PositionResumePo;
import com.neusoft.ehr.entity.po.ViewDepartmentResumePo;
import com.neusoft.ehr.entity.po.ViewPositionResumePo;
import com.neusoft.ehr.mapper.*;
import com.neusoft.ehr.service.IReverseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ReverseService implements IReverseService {

    @Autowired
    private ViewResumeDepartmentMapper viewResumeDepartmentMapper;
    @Autowired
    private DepartmentResumeMapper departmentResumeMapper;
    @Autowired
    private ViewResumePositionMapper viewResumePositionMapper;
    @Autowired
    private PositionResumeMapper positionResumeMapper;
    @Override
    public void reverseDepartment(ReverseDepartmentDto data) {
        //查到最新部门履历id
        Long id = viewResumeDepartmentMapper.selectOne(new QueryWrapper<ViewDepartmentResumePo>().eq("em_Id", data.getId())).getId();

        //完善履历
        departmentResumeMapper.update(new UpdateWrapper<DepartmentResumePo>().eq("id",id).set("end", LocalDate.now()));

        //插入新履历
        DepartmentResumePo newResume = new DepartmentResumePo();
        newResume.setEmployee(data.getId());
        newResume.setStart(LocalDate.now().plusDays(1));
        newResume.setDepartment(data.getDepartment());
        departmentResumeMapper.insert(newResume);

    }

    @Override
    public void reversePosition(ReversePositionDto data) {
        //查到最新职位履历id
        Long id = viewResumePositionMapper.selectOne(new QueryWrapper<ViewPositionResumePo>().eq("em_id", data.getId())).getId();

        //完善履历
        positionResumeMapper.update(new UpdateWrapper<PositionResumePo>().eq("id",id).set("start",LocalDate.now()));

        //插入新履历
        PositionResumePo newResume = new PositionResumePo();
        newResume.setEmployee(data.getId());
        newResume.setStart(LocalDate.now().plusDays(1));
        newResume.setPosition(data.getPosition());
        positionResumeMapper.insert(newResume);

    }
}
