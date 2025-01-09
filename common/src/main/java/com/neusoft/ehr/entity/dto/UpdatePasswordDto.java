package com.neusoft.ehr.entity.dto;
/**
 * @author 吉兆鹏
 * <p>
 * 修改密码传输对象
 */
public class UpdatePasswordDto {
    private String oldPassword;
    private String newPassword;

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }
}
