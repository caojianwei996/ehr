package com.neusoft.ehr.controller;

import com.neusoft.ehr.entity.Response;
import com.neusoft.ehr.entity.vo.MenuVo;
import com.neusoft.ehr.service.IMenuService;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/menus")
@RestController
public class MenuController extends BaseController {
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

    @GetMapping
    public Response<List<MenuVo>> select() {
        return success(menuService.select());
    }
}
