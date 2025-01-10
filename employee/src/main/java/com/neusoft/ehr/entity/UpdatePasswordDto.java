package com.neusoft.ehr.entity;

import lombok.Data;

/**
 * @author 吉兆鹏
 * <p>
 * 修改密码传输对象
 */
@Data
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;
}
