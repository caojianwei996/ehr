package com.neusoft.ehr.mapper;

import com.neusoft.ehr.entity.po.MenusPo;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.neusoft.ehr.entity.vo.MenuVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 菜单 Mapper 接口
 * </p>
 *
 * @author 曹健伟
 * @since 2025-01-08
 */
public interface MenusMapper extends BaseMapper<MenusPo> {
    List<MenuVo> select(@Param("authority") String authority);
}
