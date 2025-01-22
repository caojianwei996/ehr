package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
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
@TableName("view_supplements")
@Schema(name = "ViewSupplementsPo", description = "VIEW")
public class ViewSupplementsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "补签编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工编号")
    @TableField("employee_id")
    private Long employeeId;

    @Schema(description = "员工姓名")
    @TableField("employee_name")
    private String employeeName;

    @Schema(description = "出勤时间")
    @TableField("clock_in_old")
    private LocalDateTime clockInOld;

    @Schema(description = "新出勤时间")
    @TableField("clock_in_new")
    private LocalDateTime clockInNew;

    @Schema(description = "退勤时间")
    @TableField("clock_out_old")
    private LocalDateTime clockOutOld;

    @Schema(description = "新退勤时间")
    @TableField("clock_out_new")
    private LocalDateTime clockOutNew;

    @Schema(description = "补签原因")
    @TableField("reason")
    private String reason;
}
