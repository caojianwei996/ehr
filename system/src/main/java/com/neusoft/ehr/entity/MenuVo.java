package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-13
 */
@Data
@Schema(name = "MenuVo", description = "VIEW")
public class MenuVo {
    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单文字")
    private String text;
    @Schema(description = "菜单路径")
    private String path;
}
