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
 * 补签
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("supplements")
@Schema(name = "SupplementsPo", description = "补签")
public class SupplementsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "补签编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "补签员工")
    @TableField("employee")
    private Long employee;

    @Schema(description = "新出勤时间")
    @TableField("clock_in")
    private LocalDateTime clockIn;

    @Schema(description = "新退勤时间")
    @TableField("clock_out")
    private LocalDateTime clockOut;

    @Schema(description = "补签状态:0.已申请;1.已批准;")
    @TableField("status")
    private Byte status;
}
