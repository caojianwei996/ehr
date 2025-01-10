package com.neusoft.ehr.entity.vo;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginVo {
    private Long id;
    private String name;
    private String email;
    private Byte authority;
    private String token;
}
