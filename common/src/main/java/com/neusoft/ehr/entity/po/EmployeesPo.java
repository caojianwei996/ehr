package com.neusoft.ehr.entity.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDate;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 员工
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-09
 */
@Getter
@Setter
@TableName("employees")
@Schema(name = "EmployeesPo", description = "员工")
public class EmployeesPo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Schema(description = "员工编号")
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @Schema(description = "员工姓名")
    @TableField("name")
    private String name;

    @Schema(description = "员工邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "员工密码")
    @TableField("password")
    private String password;

    @Schema(description = "员工权限")
    @TableField("authority")
    private Byte authority;

    @Schema(description = "入职时间")
    @TableField("induction")
    private LocalDate induction;

    @Schema(description = "调休假")
    @TableField("transfer_vocations")
    private Byte transferVocations;

    @Schema(description = "员工工作类型")
    @TableField("work_type")
    private Long workType;

    @Schema(description = "员工性别")
    @TableField("gender")
    private Long gender;

    @Schema(description = "员工部门")
    @TableField("department")
    private Long department;

    @Schema(description = "员工岗位")
    @TableField("position")
    private Long position;

    @Schema(description = "员工领导")
    @TableField("leader")
    private Long leader;
}
