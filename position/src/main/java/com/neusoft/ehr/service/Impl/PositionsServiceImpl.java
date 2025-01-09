package com.neusoft.ehr.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.neusoft.ehr.DTO.PositionsDTO;
import com.neusoft.ehr.entity.ServiceCode;
import com.neusoft.ehr.entity.ServiceException;
import com.neusoft.ehr.entity.po.PositionsPo;
import com.neusoft.ehr.mapper.PositionsMapper;
import com.neusoft.ehr.service.PositionsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PositionsServiceImpl implements PositionsService {

    @Autowired
    private PositionsMapper positionsMapper;

   @Override
    public List<PositionsPo> pagePositions(Integer limit, Integer page) {
        Page<PositionsPo> positionsPage = null;
        if (limit != null && page != null) {
            Page<PositionsPo> pageParam = new Page<>(page, limit);
            QueryWrapper<PositionsPo> queryWrapper = new QueryWrapper<>();
            positionsPage = positionsMapper.selectPage(pageParam, queryWrapper);
        } else {
            // 如果没有分页参数，查询全部部门信息
            positionsPage = new Page<>();
            List<PositionsPo> allDepartments = positionsMapper.selectList(null);
            positionsPage.setRecords(allDepartments);
            positionsPage.setTotal(allDepartments.size());
        }
        List<PositionsPo> positionsList = positionsPage.getRecords();
        return positionsList;
    }

    @Override
    public void insertPositions(PositionsDTO positionsDTO) {
        // 检查岗位名称是否已存在
        QueryWrapper<PositionsPo> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", positionsDTO.getName());
        if (positionsMapper.selectCount(queryWrapper) > 0) {
            throw new ServiceException(ServiceCode.NAME_CONFLICT);
        }

        PositionsPo departmentsPo = new PositionsPo();
        BeanUtils.copyProperties(positionsDTO, departmentsPo);

        positionsMapper.insert(departmentsPo);
    }

    @Override
    public PositionsPo updatePositions(PositionsDTO positionsDTO) {
        // 先根据id获取原部门名称
        PositionsPo originalDept = positionsMapper.selectById(positionsDTO.getId());

        // 检查新名称是否与其他部门冲突（除了自身外）
        if (!originalDept.getName().equals(positionsDTO.getName())) {
            QueryWrapper<PositionsPo> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("name", positionsDTO.getName());
            if (positionsMapper.selectCount(queryWrapper) > 0) {
                throw new ServiceException(ServiceCode.NAME_CONFLICT);
            }
        }

        PositionsPo positionsPo = new PositionsPo();
        BeanUtils.copyProperties(positionsDTO, positionsPo);
        positionsMapper.updateById(positionsPo);
        return positionsPo;
    }
}
