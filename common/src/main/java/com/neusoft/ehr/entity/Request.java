package com.neusoft.ehr.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = false)
@Getter
@ToString
@Valid
public class Request<T> {
    @NotNull
    private final T data;

    @JsonCreator
    public Request(@JsonProperty("data") T data) {
        this.data = data;
    }
}
