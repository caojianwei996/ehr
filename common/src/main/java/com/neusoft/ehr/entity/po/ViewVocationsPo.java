package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * VIEW
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-09
 */
@Getter
@Setter
@TableName("view_vocations")
@Schema(name = "ViewVocationsPo", description = "VIEW")
public class ViewVocationsPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @TableField("last")
    private BigDecimal last;
}
