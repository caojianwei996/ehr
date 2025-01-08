package com.neusoft.ehr.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;

public class MenuVo {
    @JsonFormat(shape = JsonFormat.Shape.STRING)
    @Schema(description = "菜单编号")
    private Long id;

    @Schema(description = "菜单名称")
    private String name;

    @Schema(description = "菜单路径")
    private String path;

    @Schema(description = "是菜单")
    private Boolean menu;

    @Schema(description = "父菜单")
    private MenuVo parent;
}
