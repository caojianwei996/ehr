package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.ScheduleTypeDto;
import com.neusoft.ehr.entity.po.WorkTypesPo;
import com.neusoft.ehr.mapper.WorkTypesMapper;
import com.neusoft.ehr.service.IScheduleTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class ScheduleTypeService implements IScheduleTypeService {
    private final WorkTypesMapper workTypesMapper;

    @Override
    public IPage<WorkTypesPo> getScheduleTypes(Integer page, Integer size) {
        return workTypesMapper.selectPage(Page.of(page, size), Wrappers.emptyWrapper());
    }

    @Override
    public void addScheduleTypes(ScheduleTypeDto dto) {
        WorkTypesPo workTypesPo = new WorkTypesPo();
        workTypesPo.setName(dto.getName());
        workTypesPo.setOnTime(dto.getOnTime());
        workTypesPo.setOffTime(dto.getOffTime());
        workTypesMapper.insert(workTypesPo);
    }

    @Override
    public void deleteScheduleTypes(Long id) {
        workTypesMapper.deleteById(id);
    }
}
