package com.neusoft.ehr.service;

import com.neusoft.ehr.DTO.PositionsDTO;
import com.neusoft.ehr.entity.po.PositionsPo;

import java.util.List;

//mybatis-plus:

public interface PositionsService {
    List<PositionsPo> pagePositions(Integer limit, Integer page);

    void insertPositions(PositionsDTO data);

    PositionsPo updatePositions(PositionsDTO data);
}
