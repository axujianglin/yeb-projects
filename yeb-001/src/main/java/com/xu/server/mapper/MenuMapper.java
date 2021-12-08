package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.Menu;
import com.xu.server.pojo.Roles;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface MenuMapper extends BaseMapper<Menu> {


    List<Menu> getMenuByAdminId(Integer id);

    /**
     * 通过角色获取菜单列表
     * @return
     */
    List<Menu> getMenusByRole();

    /**
     * 通过特定角色获取菜单列表
     * @param rid
     * @return
     */
    List<Menu> getMenuByRole2(String rid);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAllMenus();

}
