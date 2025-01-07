package com.neusoft.ehr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @param <T> 载荷类型
 * @author 曹健伟
 * <p>
 * 响应对象
 */
@AllArgsConstructor
@Getter
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public class Response<T> {
    /**
     * 业务码
     */
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private final int code;
    /**
     * 提示消息
     */
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final String message;
    /**
     * 载荷数据
     */
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private final T data;
}
