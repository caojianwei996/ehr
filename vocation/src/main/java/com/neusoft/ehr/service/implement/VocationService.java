package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.dto.ViewVocationsDto;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.*;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.mapper.*;
import com.neusoft.ehr.service.IVocationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class VocationService implements IVocationService {
    private final CalendarMapper calendarMapper;
    private final ViewVocationsMapper viewVocationsMapper;
    private final VocationsMapper vocationsMapper;
    private final LeavesMapper leavesMapper;
    private final ViewEmployeesMapper viewEmployeesMapper;
    private final EmployeesMapper employeesMapper;

    @Override
    public Short getLast() {
        return Optional.ofNullable(
                viewEmployeesMapper.selectById(
                        AuthorizationInterceptor.getCurrentUser().getId()
                )
        ).map(ViewEmployeesPo::getLast).orElse(BigDecimal.ZERO).shortValue();
    }

    @Override
    public IPage<VocationsPo> getVocations(Integer page, Integer size) {
        return vocationsMapper.selectPage(
                Page.of(page, size), Wrappers.<VocationsPo>lambdaQuery()
                        .eq(VocationsPo::getEmployee, AuthorizationInterceptor.getCurrentUser().getId())
                        .orderByDesc(VocationsPo::getStart)
        );
    }

    @Override
    public void applyVocation(ViewVocationsDto dto) {
        LeavesPo leavesPo = leavesMapper.selectById(dto.getType());
        Long vocationLength = calendarMapper.selectCount(
                Wrappers.<CalendarPo>lambdaQuery()
                        .between(CalendarPo::getId, dto.getStart(), dto.getEnd())
                        .in(CalendarPo::getType, 2, 4)
        );
        if (leavesPo.getRestricted() && vocationLength > getLast()) {
            throw new ServiceException(ServiceCode.HOLIDAY_NOT_ENOUGH);
        }
        VocationsPo vocationsPo = new VocationsPo();
        vocationsPo.setEmployee(AuthorizationInterceptor.getCurrentUser().getId());
        vocationsPo.setType(dto.getType());
        vocationsPo.setStart(dto.getStart());
        vocationsPo.setEnd(dto.getEnd());
        vocationsPo.setLength(vocationLength);
        vocationsPo.setStatus((byte) 0);
        vocationsMapper.insert(vocationsPo);
    }

    @Override
    public List<ViewVocationsPo> getApply() {
        List<Long> collect = employeesMapper.selectList(
                Wrappers.<EmployeesPo>lambdaQuery().eq(
                        EmployeesPo::getLeader,
                        AuthorizationInterceptor.getCurrentUser().getId()
                )
        ).stream().map(EmployeesPo::getId).collect(Collectors.toList());
        if (collect.isEmpty()) {
            return new ArrayList<>();
        } else {
            return viewVocationsMapper.selectList(
                    Wrappers.<ViewVocationsPo>lambdaQuery().in(ViewVocationsPo::getEmployeeId, collect)
            );
        }

    }

    @Override
    public void updateApply(ApplyDto dto) {
        VocationsPo vocationsPo = vocationsMapper.selectById(dto.getId());
        if (vocationsPo != null) {
            vocationsPo.setStatus((byte) (dto.getAgree() ? 1 : 2));
            vocationsMapper.updateById(vocationsPo);
        }
    }
}
