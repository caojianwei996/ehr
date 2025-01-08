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
 * @since 2025-01-08
 */
@Getter
@Setter
@TableName("messages")
@Schema(name = "MessagesPo", description = "消息")
public class MessagesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "消息编号")
    @TableId(value = "code", type = IdType.AUTO)
    private Long code;

    @Schema(description = "语言")
    @TableId("language")
    private Long language;

    @Schema(description = "国家")
    @TableId("country")
    private Long country;

    @Schema(description = "文本")
    @TableField("text")
    private String text;
}
