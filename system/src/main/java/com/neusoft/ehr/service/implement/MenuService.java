package com.neusoft.ehr.service.implement;

import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.neusoft.ehr.entity.po.MenusPo;
import com.neusoft.ehr.entity.MenuVo;
import com.neusoft.ehr.entity.po.MessagesPo;
import com.neusoft.ehr.interceptor.authorization.AuthorizationInterceptor;
import com.neusoft.ehr.mapper.MenusMapper;
import com.neusoft.ehr.mapper.MessagesMapper;
import com.neusoft.ehr.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
    private final MessagesMapper messagesMapper;

    /**
     * 获取菜单
     *
     * @return 菜单组件
     */
    @Override
    public List<MenuVo> select() {
        List<MenusPo> menusPos = menusMapper.selectList(
                Wrappers.<MenusPo>lambdaQuery()
                        .le(MenusPo::getAuthority, AuthorizationInterceptor.getCurrentUser().getAuthority())
        );
        Map<String, String> map = new HashMap<>();
        List<String> collect = menusPos.stream().map(MenusPo::getName).collect(Collectors.toList());
        messagesMapper.selectList(
                Wrappers.<MessagesPo>lambdaQuery()
                        .in(MessagesPo::getCode, collect)
                        .eq(MessagesPo::getLanguage, LocaleContextHolder.getLocale().getLanguage())
        ).forEach(po -> map.put(po.getCode(), po.getText()));
        return menusPos.stream()
                .map(po -> {
                    MenuVo menuVo = new MenuVo();
                    menuVo.setName(po.getName());
                    String text = map.get(po.getName());
                    if (StringUtils.hasText(text)) {
                        menuVo.setText(text);
                    } else {
                        menuVo.setText(po.getName());
                    }
                    menuVo.setPath(po.getPath());
                    return menuVo;
                })
                .collect(Collectors.toList());
    }
}
