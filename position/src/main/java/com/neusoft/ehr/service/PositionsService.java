package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.InsertPositionsDto;
import com.neusoft.ehr.entity.UpdatePositionsDto;
import com.neusoft.ehr.entity.po.PositionsPo;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;

public interface PositionsService {
    IPage<PositionsPo> selectPositions(Integer page, Integer limit);

    void insertPositions(InsertPositionsDto data);

    void updatePositions(UpdatePositionsDto data);

    PositionsPo selectOne(Long id);

    IPage<ViewEmployeesPo> selectEmployeesByPositionId(Long positionId, Integer page, Integer limit);
}
