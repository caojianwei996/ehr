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
@TableName("view_attendances_day")
@Schema(name = "ViewAttendancesDayPo", description = "VIEW")
public class ViewAttendancesDayPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "考勤编号")
    @TableField("id")
    private Long id;

    @Schema(description = "考勤员工")
    @TableField("employee")
    private Long employee;

    @Schema(description = "出勤时间")
    @TableField("clock_in")
    private LocalDateTime clockIn;

    @Schema(description = "退勤时间")
    @TableField("clock_out")
    private LocalDateTime clockOut;
}
