package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.MenusPo;
import com.neusoft.ehr.entity.vo.MenuVo;
import com.neusoft.ehr.mapper.MenusMapper;
import com.neusoft.ehr.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 菜单业务实现
 *
 * @author 曹健伟
 */
@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {
    /**
     * 菜单仓库组件
     */
    private final MenusMapper menusMapper;

    /**
     * 获取菜单
     *
     * @return 菜单组件
     */
    @Override
    public List<MenuVo> select() {
        return menusMapper
                .selectList(Wrappers.<MenusPo>lambdaQuery().le(MenusPo::getAuthority, 2))
                .stream()
                .map(menusPo -> {
                    MenuVo vo = new MenuVo();
                    vo.setName(menusPo.getName());
                    vo.setPath(menusPo.getPath());
                    vo.setAuthority(MenuVo.Authority.values()[menusPo.getAuthority()]);
                    return vo;
                })
                .collect(Collectors.toList());
    }
}
