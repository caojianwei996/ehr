package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Schema(name = "UpdatePositionsDto", description = "修改岗位参数接收")
@Data
public class UpdatePositionsDto {
    @Schema(description = "岗位编号")
    @NotNull
    private Long id;
    @Schema(description = "岗位名称")
    private String name;
    @Schema(description = "岗位级别")
    private Byte level;
    @Schema(description = "岗位状态")
    private Byte status;
}
