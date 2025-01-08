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
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("view_attendances_month")
@Schema(name = "ViewAttendancesMonthPo", description = "VIEW")
public class ViewAttendancesMonthPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    @TableField("employee")
    private String employee;

    @Schema(description = "出勤时间")
    @TableField("clock_in")
    private LocalDateTime clockIn;

    @Schema(description = "退勤时间")
    @TableField("clock_out")
    private LocalDateTime clockOut;
}
