package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalTime;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 工作类型
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("work_types")
@Schema(name = "WorkTypesPo", description = "工作类型")
public class WorkTypesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "工作类型编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "工作类型名称")
    @TableField("name")
    private String name;

    @Schema(description = "上班时间")
    @TableField("on_time")
    private LocalTime onTime;

    @Schema(description = "下班时间")
    @TableField("off_time")
    private LocalTime offTime;
}
