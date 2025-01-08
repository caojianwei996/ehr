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
 * 国家
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("countries")
@Schema(name = "CountriesPo", description = "国家")
public class CountriesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "国家编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "国家名称")
    @TableField("name")
    private String name;
}
