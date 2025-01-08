package com.neusoft.ehr.service.implement;

import com.neusoft.ehr.entity.vo.MenuVo;
import com.neusoft.ehr.mapper.MenusMapper;
import com.neusoft.ehr.service.IMenuService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MenuService implements IMenuService {
    private final MenusMapper menusMapper;

    @Override
    public List<MenuVo> select() {
        return menusMapper.select("");
    }
}
