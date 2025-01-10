package com.neusoft.ehr.entity;

import lombok.Data;

/**
 * @author 吉兆鹏
 * <p>
 * 登录数据传输对象
 */
@Data
public class LoginDto {
    private String username;
    private String password;
}
