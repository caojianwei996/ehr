package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 性别
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("genders")
@Schema(name = "GendersPo", description = "性别")
public class GendersPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "性别编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "性别名称")
    @TableField("name")
    private String name;
}
