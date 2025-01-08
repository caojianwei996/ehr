package com.netsoft.entity.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author xkf
 * @Date 2025/1/8 16:24
 * @DESCRIBTE
 */
@Getter
@Setter
@Schema(name = "ViewAttendancesMonthVo", description = "月出勤记录")
public class ViewAttendancesMonthVo {

    @Schema(description = "员工姓名")
    private String name;

    @Schema(description = "出勤时间")
    private LocalDateTime clockIn;

    @Schema(description = "退勤时间")
    private LocalDateTime clockOut;

    @Schema(description = "缺勤类型")
    private String type;
}
