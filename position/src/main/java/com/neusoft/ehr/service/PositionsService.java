package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.InsertPositionsDto;
import com.neusoft.ehr.entity.UpdatePositionsDto;
import com.neusoft.ehr.entity.po.PositionsPo;

import java.util.List;

//mybatis-plus:

public interface PositionsService {
    IPage<PositionsPo> selectPositions(Integer limit, Integer page);

    void insertPositions(InsertPositionsDto data);

    void updatePositions(UpdatePositionsDto data);
}
