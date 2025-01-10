package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <p>
 * 部门履历表
 * </p>
 *
 * @author 吉兆鹏
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("position_resume")
@Schema(name = "PositionResumePo", description = "部门履历")
public class PositionResumePo {

    @Schema(description = "主键id")
    @TableField("id")
    private Long id;
    @Schema(description = "员工id")
    @TableField("employee")
    private Long employee;
    @Schema(description = "起始时间")
    @TableField("start")
    private LocalDate start;
    @Schema(description = "终止时间")
    @TableField("end")
    private LocalDate end;
    @Schema(description = "职位id")
    @TableField("position")
    private Long position;
}
