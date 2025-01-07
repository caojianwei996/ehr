package com.neusoft.ehr.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author 曹健伟
 * <p>
 * 业务异常
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ServiceException extends RuntimeException {
    /**
     * 业务码
     */
    private final ServiceCode serviceCode;
}
