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
@TableName("view_vocations")
@Schema(name = "ViewVocationsPo", description = "VIEW")
public class ViewVocationsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "休假编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工编号")
    @TableField("employee_id")
    private Long employeeId;

    @Schema(description = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @Schema(description = "假期类型")
    @TableField("type")
    private Long type;

    @Schema(description = "开始时间")
    @TableField("start")
    private LocalDate start;

    @Schema(description = "结束时间")
    @TableField("end")
    private LocalDate end;

    @Schema(description = "休假长度")
    @TableField("length")
    private Long length;
}
