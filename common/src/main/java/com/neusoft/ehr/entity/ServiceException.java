package com.neusoft.ehr.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
public class ServiceException extends RuntimeException {
    private final ServiceCode serviceCode;

    public ServiceException(ServiceCode serviceCode) {
        this.serviceCode = serviceCode;
    }
}
