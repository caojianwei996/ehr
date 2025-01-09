package com.neusoft.ehr.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DepartmentsDTO {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("name")
    private String name;
    @TableField("preparation")
    private Long preparation;
}
