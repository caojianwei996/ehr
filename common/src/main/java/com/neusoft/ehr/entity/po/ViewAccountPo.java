package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
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
@TableName("view_account")
@Schema(name = "ViewAccountPo", description = "VIEW")
public class ViewAccountPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工编号")
    @TableField("id")
    private Long id;

    @Schema(description = "员工邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "员工密码")
    @TableField("password")
    private String password;
}
