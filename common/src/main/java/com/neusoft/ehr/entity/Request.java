package com.neusoft.ehr.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * @param <T> 载荷类型
 * @author 曹健伟
 * <p>
 * 请求对象
 */
@Getter
@Valid
public class Request<T> {
    /**
     * 载荷数据
     */
    @NotNull
    private final T data;

    @JsonCreator
    public Request(@JsonProperty("data") T data) {
        this.data = data;
    }
}
