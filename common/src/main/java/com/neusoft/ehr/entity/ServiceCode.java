package com.neusoft.ehr.entity;

/**
 * @author 曹健伟
 * <p>
 * 业务码
 */
public enum ServiceCode {
    /**
     * 操作成功
     */
    SUCCESS,
    /**
     * 操作失败
     */
    FAILURE,
    /**
     * Token错误
     */
    TOKEN_INCORRECT,
    /**
     * 用户不存在
     */
    USER_NOT_EXIST,
    /**
     * 密码错误
     */
    PASSWORD_ERROR,
    /**
     * 属性冲突
     */
    PROPERTY_CONFLICT,
}
