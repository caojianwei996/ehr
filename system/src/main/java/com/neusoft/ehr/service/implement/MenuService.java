package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.MenusPo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.mapper.MenusMapper;
import com.neusoft.ehr.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<MenusPo> select() {
        return menusMapper.selectList(
                Wrappers.<MenusPo>lambdaQuery().le(
                        MenusPo::getAuthority, AuthorizationInterceptor.getCurrentUser().getAuthority()
                )
        );
    }
}
