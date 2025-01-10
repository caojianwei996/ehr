package com.neusoft.ehr.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.neusoft.ehr.dto.ViewVocationsDto;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.ViewVocationsPo;
import com.neusoft.ehr.entity.po.VocationsPo;

import java.util.List;

public interface IVocationService {
    Short getLast();

    IPage<VocationsPo> getVocations(Integer page, Integer size);

    void applyVocation(ViewVocationsDto dto);

    List<ViewVocationsPo> getApply();

    void updateApply(ApplyDto dto);
}
