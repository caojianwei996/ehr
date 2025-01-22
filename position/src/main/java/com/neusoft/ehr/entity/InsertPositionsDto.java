package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Schema(name = "InsertPositionsDto", description = "新增岗位参数接收")
@Data
public class InsertPositionsDto {
    @Schema(description = "岗位名称")
    @NotBlank
    @Size(min = 1, max = 64)
    private String name;
    @Schema(description = "岗位等级")
    @NotBlank
    private Byte level;

}
