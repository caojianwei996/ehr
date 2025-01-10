package com.neusoft.ehr.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * @author xkf
 */
@Getter
@Setter
@Schema(name = "ApplyDto", description = "申请参数接收")
public class ApplyDto {
    @Schema(description = "申请编号")
    private Long id;
    @Schema(description = "是否同意")
    private Boolean agree;
}
