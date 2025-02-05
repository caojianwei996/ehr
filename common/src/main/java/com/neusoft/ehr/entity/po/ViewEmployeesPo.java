package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-15
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

    @Schema(description = "员工性别")
    @TableField("gender")
    private Byte gender;

    @Schema(description = "部门编号")
    @TableField("department_id")
    private Long departmentId;

    @Schema(description = "部门名称")
    @TableField("department")
    private String department;

    @Schema(description = "岗位编号")
    @TableField("position_id")
    private Long positionId;

    @Schema(description = "岗位名称")
    @TableField("position")
    private String position;

    @Schema(description = "员工姓名")
    @TableField("leader")
    private String leader;

    @Schema(description = "入职时间")
    @TableField("induction")
    private LocalDate induction;

    @Schema(description = "员工生日")
    @TableField("birthday")
    private LocalDate birthday;

    @TableField("last")
    private BigDecimal last;
}
