package com.neusoft.ehr.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author 吉兆鹏
 * <p>
 * 登录数据传输对象
 */
@Schema(name = "LoginDto", description = "登录参数接收")
@Data
public class LoginDto {
    @Schema(description = "账号")
    @NotBlank
    @Email
    private String username;
    @Schema(description = "密码")
    @NotBlank
    @Size(min = 6, max = 20)
    private String password;
}
