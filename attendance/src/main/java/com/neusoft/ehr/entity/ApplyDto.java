package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xkf
 */
@Getter
@Setter
@Schema(name = "ApplyDto", description = "审批休假参数接收")
public class ApplyDto {
    @Schema(description = "补签编号")
    private Long id;
    @Schema(description = "是否同意")
    private Boolean agree;
}
