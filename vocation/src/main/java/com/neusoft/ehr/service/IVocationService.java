package com.neusoft.ehr.service;

import com.neusoft.ehr.dto.ViewVocationsDto;
import com.neusoft.ehr.entity.dto.ApplyDto;
import com.neusoft.ehr.entity.po.ViewVocationsPo;

import javax.validation.constraints.NotNull;
import java.util.List;

public interface IVocationService {
    Short getVocations();

    void applyVocation(ViewVocationsDto dto);

    List<ViewVocationsPo> getApply();

    void updateApply(ApplyDto dto);
}
