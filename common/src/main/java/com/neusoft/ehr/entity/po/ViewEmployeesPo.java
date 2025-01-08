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
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("view_employees")
@Schema(name = "ViewEmployeesPo", description = "VIEW")
public class ViewEmployeesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "员工邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "员工权限")
    @TableField("authority")
    private Long authority;

    @Schema(description = "工作类型名称")
    @TableField("work_type")
    private String workType;

    @Schema(description = "性别名称")
    @TableField("gender")
    private String gender;

    @Schema(description = "部门名称")
    @TableField("department")
    private String department;

    @Schema(description = "岗位名称")
    @TableField("position")
    private String position;

    @Schema(description = "员工姓名")
    @TableField("leader")
    private String leader;
}
