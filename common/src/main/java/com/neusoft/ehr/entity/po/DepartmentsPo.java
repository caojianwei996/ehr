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
 * 部门
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-09
 */
@Getter
@Setter
@TableName("departments")
@Schema(name = "DepartmentsPo", description = "部门")
public class DepartmentsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "部门编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "部门名称")
    @TableField("name")
    private String name;

    @Schema(description = "部门编制")
    @TableField("preparation")
    private Long preparation;

    @Schema(description = "部门状态:0.正常;1.关闭;")
    @TableField("status")
    private Byte status;

    @Schema(description = "部门领导")
    @TableField("leader")
    private Long leader;
}
