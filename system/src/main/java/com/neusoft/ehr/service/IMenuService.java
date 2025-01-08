package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.vo.MenuVo;

import java.util.List;

public interface IMenuService {
    List<MenuVo> select();
}
