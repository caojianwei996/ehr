package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

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
@TableName("view_messages")
@Schema(name = "ViewMessagesPo", description = "VIEW")
public class ViewMessagesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "消息编号")
    @TableField("id")
    @Null
    private Long id;

    @Schema(description = "消息代码")
    @TableField("code")
    @NotBlank
    @Size(min = 1, max = 64)
    private String code;

    @Schema(description = "语言名称")
    @TableField("language")
    @NotBlank
    @Size(min = 2, max = 2)
    private String language;

    @Schema(description = "国家名称")
    @TableField("country")
    @NotBlank
    @Size(min = 2, max = 2)
    private String country;

    @Schema(description = "文本")
    @TableField("text")
    @NotBlank
    private String text;
}
