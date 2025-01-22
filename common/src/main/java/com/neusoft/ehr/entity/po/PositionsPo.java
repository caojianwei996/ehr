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
 * 岗位
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-15
 */
@Getter
@Setter
@TableName("positions")
@Schema(name = "PositionsPo", description = "岗位")
public class PositionsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "岗位编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "岗位名称")
    @TableField("name")
    private String name;

    @Schema(description = "岗位级别")
    @TableField("level")
    private Byte level;

    @Schema(description = "岗位状态:0.正常;1.关闭;")
    @TableField("status")
    private Byte status;
}
