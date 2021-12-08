package com.xu.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.server.pojo.Menu;
import com.xu.server.pojo.Roles;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface IMenuService extends IService<Menu> {
    /**
     * 根据用户id获取菜单列表
     * @return
     */
    List<Menu> getMenuByAdminId();

    /**
     * 根据角色获取菜单列表
     * @return
     */
    List<Menu> getMenuByRole();

    /**
     * 根据特定用户获取菜单列表
     * @param id
     * @return
     */
    List<Menu> getMenuByRole2(String id);

    /**
     * 获取所有菜单
     * @return
     */
    List<Menu> getAllMenus();
}
