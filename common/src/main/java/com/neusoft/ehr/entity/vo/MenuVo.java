package com.neusoft.ehr.entity.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * 菜单
 *
 * @author 曹健伟
 */
@Data
@Schema(name = "MenuVo", description = "菜单")
public class MenuVo {
    public enum Authority {
        USER, ADMIN, SYSTEM
    }

    @Schema(description = "菜单名称")
    private String name;
    @Schema(description = "菜单路径")
    private String path;
    @Schema(description = "权限级别")
    private Authority authority;
}
