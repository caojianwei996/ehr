package com.neusoft.ehr.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author xkf
 */
@Data
@Schema(name = "ApplyDto", description = "申请参数接收")
public class ApplyDto {
    @Schema(description = "申请编号")
    @NotNull
    private Long id;
    @Schema(description = "是否同意")
    @NotNull
    private Boolean agree;
}
