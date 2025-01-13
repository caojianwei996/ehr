package com.neusoft.ehr.service.Impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.entity.InsertPositionsDto;
import com.neusoft.ehr.entity.UpdatePositionsDto;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.PositionsPo;
import com.neusoft.ehr.entity.po.ViewEmployeesPo;
import com.neusoft.ehr.mapper.PositionsMapper;
import com.neusoft.ehr.mapper.ViewEmployeesMapper;
import com.neusoft.ehr.service.PositionsService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class PositionsServiceImpl implements PositionsService {

    private final PositionsMapper positionsMapper;
    private final ViewEmployeesMapper viewEmployeesPoMapper;

    @Override
    public IPage<PositionsPo> selectPositions(String name, Byte status, Integer page, Integer limit) {
        //根据岗位名称（非必须）以及状态（非必须）查询岗位
        return positionsMapper.selectPage(
                Page.of(page, limit),
                Wrappers.<PositionsPo>lambdaQuery()
                        .like(!name.equals(""), PositionsPo::getName, name)
                        .eq(status != null, PositionsPo::getStatus, status)
        );
    }

    @Override
    public void insertPositions(InsertPositionsDto insertPositionsDTO) {
        // 检查岗位名称是否已存在
        if (positionsMapper
                .exists(
                        Wrappers.<PositionsPo>lambdaQuery().eq(
                                PositionsPo::getName, insertPositionsDTO.getName()
                        )
                )
        ) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }

        PositionsPo departmentsPo = new PositionsPo();
        BeanUtils.copyProperties(insertPositionsDTO, departmentsPo);
        positionsMapper.insert(departmentsPo);
    }

    @Override
    public void updatePositions(UpdatePositionsDto updatePositionsDTO) {
        // 检查新名称是否与其他部门冲突
        if (positionsMapper
                .exists(
                        Wrappers.<PositionsPo>lambdaQuery().eq(
                                PositionsPo::getName, updatePositionsDTO.getName()
                        )
                )
        ) {
            throw new ServiceException(ServiceCode.PROPERTY_CONFLICT);
        }

        PositionsPo positionsPo = positionsMapper.selectById(updatePositionsDTO.getId());
        if (positionsPo == null) {
            return;
        }
        if (updatePositionsDTO.getName() != null) {
            positionsPo.setName(updatePositionsDTO.getName());
        }
        if (updatePositionsDTO.getLevel() != null) {
            positionsPo.setLevel(updatePositionsDTO.getLevel());
        }
        if (updatePositionsDTO.getStatus() != null) {
            positionsPo.setStatus(updatePositionsDTO.getStatus());
        }
        positionsMapper.updateById(positionsPo);
    }

    @Override
    public PositionsPo selectOne(Long id) {
        return positionsMapper.selectById(id);
    }

    @Override
    public IPage<ViewEmployeesPo> selectEmployeesByPositionId(Long positionId, Integer page, Integer limit) {
        return viewEmployeesPoMapper.selectPage(
                Page.of(page, limit),
                Wrappers.<ViewEmployeesPo>lambdaQuery().eq(ViewEmployeesPo::getPosition_id, positionId)
        );
    }
}
