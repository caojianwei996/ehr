package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("view_department_resume")
@Schema(name = "ViewDepartmentResumePo", description = "VIEW")
public class ViewDepartmentResumePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "履历编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工编号")
    @TableField("em_id")
    private Long emId;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "部门名称")
    @TableField("department")
    private String department;

    @Schema(description = "起始时间")
    @TableField("START")
    private LocalDate start;

    @Schema(description = "终止时间")
    @TableField("END")
    private LocalDate end;
}
