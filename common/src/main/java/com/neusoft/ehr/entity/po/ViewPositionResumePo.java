package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
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
@TableName("view_position_resume")
@Schema(name = "ViewPositionResumePo", description = "VIEW")
public class ViewPositionResumePo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "职位编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工编号")
    @TableField("em_id")
    private Long emId;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "岗位名称")
    @TableField("position")
    private String position;

    @Schema(description = "起始时间")
    @TableField("start")
    private LocalDate start;

    @Schema(description = "终止时间")
    @TableField("end")
    private LocalDate end;
}
