package com.neusoft.ehr.DTO;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class DepartmentsDTO {
    private Long id;

    private String name;

    private Long preparation;
}
