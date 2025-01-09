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
    @TableField("department_id")
    private Long departmentId;

    @Schema(description = "部门名称")
    @TableField("department_name")
    private String departmentName;

    @Schema(description = "员工姓名")
    @TableField("department_leader")
    private String departmentLeader;

    @Schema(description = "部门编制")
    @TableField("department_preparation")
    private Long departmentPreparation;

    @Schema(description = "部门状态:0.正常;1.关闭;")
    @TableField("department_status")
    private Byte departmentStatus;
}
