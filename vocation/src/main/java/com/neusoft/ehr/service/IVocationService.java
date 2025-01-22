package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.entity.AddLeavesDto;
import com.neusoft.ehr.entity.MutateLeavesDto;
import com.neusoft.ehr.entity.ViewVocationsDto;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.LeavesPo;
import com.neusoft.ehr.entity.po.ViewVocationsPo;

import java.util.List;

public interface IVocationService {
    LeavesPo getVocationType(Long id);

    List<LeavesPo> getVocationTypes();

    Short getLast();

    IPage<ViewVocationsPo> getVocations(Integer page, Integer size);

    void applyVocation(ViewVocationsDto dto);

    List<ViewVocationsPo> getApply();

    void updateApply(ApplyDto dto);

    void addVocationTypes(AddLeavesDto data);

    void mutateVocationTypes(MutateLeavesDto data);

    void removeVocationTypes(Long id);
}
