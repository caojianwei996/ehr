package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.ReverseDepartmentDto;
import com.neusoft.ehr.entity.ReversePositionDto;

import javax.validation.constraints.NotNull;

public interface IReverseService {
    void reverseDepartment(@NotNull ReverseDepartmentDto data);

    void reversePosition(@NotNull ReversePositionDto data);
}
