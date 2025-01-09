package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-09
 */
@Getter
@Setter
@TableName("view_departments")
@Schema(name = "ViewDepartmentsPo", description = "VIEW")
public class ViewDepartmentsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "部门编号")
    @TableField("id")
    private Long id;

    @Schema(description = "部门名称")
    @TableField("name")
    private String name;

    @Schema(description = "员工姓名")
    @TableField("leader")
    private String leader;

    @Schema(description = "部门编制")
    @TableField("preparation")
    private Long preparation;

    @Schema(description = "部门状态:0.正常;1.关闭;")
    @TableField("status")
    private Byte status;
}
