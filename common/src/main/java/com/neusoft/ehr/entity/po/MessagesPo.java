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
 * 消息
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-10
 */
@Getter
@Setter
@TableName("messages")
@Schema(name = "MessagesPo", description = "消息")
public class MessagesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "消息编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "消息代码")
    @TableField("code")
    private String code;

    @Schema(description = "语言")
    @TableField("language")
    private Long language;

    @Schema(description = "国家")
    @TableField("country")
    private Long country;

    @Schema(description = "文本")
    @TableField("text")
    private String text;
}
