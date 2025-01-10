package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**
 *
 * view
 *
 * @author 吉兆鹏
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("view_department_resume")
@Schema(name = "ViewResumeDepartmentVo", description = "VIEW")
public class ViewResumeDepartmentVo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "任职部门")
    @TableField("department")
    private String department;

    @Schema(description = "起始时间")
    @TableField("start")
    private LocalDate start;

    @Schema(description = "终止时间")
    @TableField("end")
    private LocalDate end;
}
