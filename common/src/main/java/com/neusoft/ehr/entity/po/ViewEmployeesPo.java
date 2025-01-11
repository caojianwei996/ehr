package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("view_employees")
@Schema(name = "ViewEmployeesPo", description = "VIEW")
public class ViewEmployeesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "员工邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "员工权限:1.普通;2.领导;3.管理;")
    @TableField("authority")
    private Byte authority;

    @Schema(description = "工作类型名称")
    @TableField("work_type")
    private String workType;

    @Schema(description = "性别名称")
    @TableField("gender")
    private String gender;

    @Schema(description = "部门编号")
    @TableField(value = "department_id")
    private Long department_id;

    @Schema(description = "部门名称")
    @TableField("department")
    private String department;

    @Schema(description = "岗位编号")
    @TableField(value = "position_id")
    private Long position_id;

    @Schema(description = "岗位名称")
    @TableField("position")
    private String position;

    @Schema(description = "员工姓名")
    @TableField("leader")
    private String leader;

    @TableField("last")
    private BigDecimal last;
}
