package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 休假
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("vocations")
@Schema(name = "VocationsPo", description = "休假")
public class VocationsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "休假编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "休假员工")
    @TableField("employee")
    private Long employee;

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

    @Schema(description = "休假状态:0.已申请;1.已批准;2.已拒绝;3.休假中;4.已结束;")
    @TableField("status")
    private Byte status;
}
