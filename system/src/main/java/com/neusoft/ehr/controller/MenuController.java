package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.po.MenusPo;
import com.neusoft.ehr.service.IMenuService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 菜单控制器
 *
 * @author 曹健伟
 */
@RequestMapping("/menus")
@RestController
public class MenuController extends BaseController {
    /**
     * 菜单业务组件
     */
    private final IMenuService menuService;

    /**
     * MenuController构造方法
     *
     * @param messageSource 国际化组件
     * @param menuService   菜单业务组件
     */
    public MenuController(MessageSource messageSource, IMenuService menuService) {
        super(messageSource);
        this.menuService = menuService;
    }

    /**
     * 获取菜单
     *
     * @return 菜单列表
     */
    @GetMapping
    public Response<List<MenusPo>> select() {
        return success(menuService.select());
    }
}
