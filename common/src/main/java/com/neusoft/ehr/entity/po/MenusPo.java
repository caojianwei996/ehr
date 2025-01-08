package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 菜单
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("menus")
@Schema(name = "MenusPo", description = "菜单")
public class MenusPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "菜单名称")
    @TableField("name")
    private String name;

    @Schema(description = "菜单路径")
    @TableField("path")
    private String path;

    @Schema(description = "是菜单")
    @TableField("is_menu")
    private Boolean menu;

    @Schema(description = "父菜单")
    @TableField("parent")
    private Long parent;

    @Schema(description = "权限级别")
    @TableField("authority")
    private Byte authority;
}
