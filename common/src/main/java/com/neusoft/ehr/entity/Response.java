package com.neusoft.ehr.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

@Data
public class Response<T> {
    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private final int code;
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    private final String message;
    @JsonFormat(shape = JsonFormat.Shape.OBJECT)
    private final T data;
}
