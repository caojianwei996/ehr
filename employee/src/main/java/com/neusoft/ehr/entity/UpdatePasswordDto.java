package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author 吉兆鹏
 * <p>
 * 修改密码传输对象
 */
@Schema(name = "UpdatePasswordDto", description = "改密参数接收")
@Data
public class UpdatePasswordDto {
    @Schema(description = "旧密码")
    @NotBlank
    private String oldPassword;
    @Schema(description = "新密码")
    @NotBlank
    private String newPassword;
}
