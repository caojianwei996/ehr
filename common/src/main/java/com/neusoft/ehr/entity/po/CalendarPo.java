package com.neusoft.ehr.entity.po;

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
 * 日历
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("calendar")
@Schema(name = "CalendarPo", description = "日历")
public class CalendarPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "日历日期")
    @TableId("id")
    private LocalDate id;

    @Schema(description = "日期类型:1.法定假日;2.法定调休日;3.普通休息日;4.普通工作日;")
    @TableField("type")
    private Byte type;
}
