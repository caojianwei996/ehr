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
@TableName("view_employees")
@Schema(name = "ViewEmployeesPo", description = "VIEW")
public class ViewEmployeesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工编号")
    @TableField("employee_id")
    private Long employeeId;

    @Schema(description = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @Schema(description = "员工邮箱")
    @TableField("employee_email")
    private String employeeEmail;

    @Schema(description = "员工权限")
    @TableField("employee_authority")
    private Byte employeeAuthority;

    @Schema(description = "工作类型名称")
    @TableField("employee_work_type")
    private String employeeWorkType;

    @Schema(description = "性别名称")
    @TableField("employee_gender")
    private String employeeGender;

    @Schema(description = "部门名称")
    @TableField("employee_department")
    private String employeeDepartment;

    @Schema(description = "岗位名称")
    @TableField("employee_position")
    private String employeePosition;

    @Schema(description = "员工姓名")
    @TableField("employee_leader")
    private String employeeLeader;
}
