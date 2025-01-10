package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * <p>
 * 消息
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-09
 */
@Data
public class MessageDto {
    @Schema(description = "消息代码")
    @NotBlank
    @Size(min = 1, max = 64)
    private String code;

    @Schema(description = "语言名称")
    @NotBlank
    @Size(min = 2, max = 2)
    private String language;

    @Schema(description = "国家名称")
    @NotBlank
    @Size(min = 2, max = 2)
    private String country;

    @Schema(description = "文本")
    @NotBlank
    private String text;
}
