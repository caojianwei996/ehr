package com.netsoft.entity.dto;

import com.neusoft.ehr.entity.po.SupplementsPo;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author xkf
 * @Date 2025/1/9 15:08
 * @DESCRIBTE
 */
@Getter
@Setter
@Schema(name = "ApplyDto", description = "审批休假参数接收")
public class ApplyDto {
    @Schema(description = "是否同意")
    private Boolean agree;

    @Schema(description = "缺勤列表")
    private List<SupplementsPo> list;

}
