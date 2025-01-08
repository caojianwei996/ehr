package com.neusoft.ehr.service;

import com.neusoft.ehr.entity.vo.MenuVo;

import java.util.List;

/**
 * 菜单业务
 *
 * @author 曹健伟
 */
public interface IMenuService {
    List<MenuVo> select();
}
