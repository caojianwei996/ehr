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
 * @since 2025-01-15
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

    @Schema(description = "签到编号")
    @TableField("attendance")
    private Long attendance;

    @Schema(description = "新出勤时间")
    @TableField("clock_in")
    private LocalDateTime clockIn;

    @Schema(description = "新退勤时间")
    @TableField("clock_out")
    private LocalDateTime clockOut;

    @Schema(description = "补签原因")
    @TableField("reason")
    private String reason;

    @Schema(description = "补签状态:0.未处理;1.已处理;")
    @TableField("is_filtered")
    private Boolean filtered;
}
