package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 考勤
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("attendances")
@Schema(name = "AttendancesPo", description = "考勤")
public class AttendancesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "考勤编号")
    @TableId(value = "id", type = IdType.AUTO)
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

    @Schema(description = "考勤状态:0.考勤中;1.出勤;2.缺勤;3.审批;4.补签;5.矿工;")
    @TableField("status")
    private Byte status;
}
