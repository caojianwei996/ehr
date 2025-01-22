package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.ScheduleTypeDto;
import com.neusoft.ehr.entity.po.WorkTypesPo;

public interface IScheduleTypeService {
    IPage<WorkTypesPo> getScheduleTypes(Integer page, Integer size);

    void addScheduleTypes(ScheduleTypeDto dto);

    void deleteScheduleTypes(Long id);
}
