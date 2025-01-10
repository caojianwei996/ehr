package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 部门履历
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("department_resume")
@Schema(name = "DepartmentResumePo", description = "部门履历")
public class DepartmentResumePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "履历编号")
    @TableId("id")
    private Long id;

    @Schema(description = "员工编号")
    @TableField("employee")
    private Long employee;

    @Schema(description = "起始时间")
    @TableField("start")
    private LocalDate start;

    @Schema(description = "终止时间")
    @TableField("end")
    private LocalDate end;

    @Schema(description = "部门编号")
    @TableField("department")
    private Long department;
}
