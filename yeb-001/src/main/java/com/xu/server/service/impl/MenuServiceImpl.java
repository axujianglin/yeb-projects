package com.xu.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.mapper.MenuMapper;
import com.xu.server.pojo.Admin;
import com.xu.server.pojo.Menu;
import com.xu.server.pojo.Roles;
import com.xu.server.service.IMenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
@SuppressWarnings("all")
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements IMenuService {
    @Autowired
    MenuMapper menuMapper;
    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * 根据用户Id获取菜单
     * @return
     */
    @Override
    public List<Menu> getMenuByAdminId() {

        Integer id = ((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId();
        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
        List<Menu> menus = (List<Menu>) valueOperations.get("menu_" + id);
        if(CollectionUtils.isEmpty(menus)){
            menus = menuMapper.getMenuByAdminId(id);
            valueOperations.set("menu_"+id,menus);
        }
        return menus;
    }

    /**
     * 根据角色获取菜单列表
     * @return
     */
    @Override
    public List<Menu> getMenuByRole() {
        return menuMapper.getMenusByRole();
    }

    /**
     * 获取所有菜单
     * @return
     */
    @Override
    public List<Menu> getAllMenus() {
        return menuMapper.getAllMenus();
    }

    /**
     * 通过特定用户获取菜单列表
     * @param id
     * @return
     */
    @Override
    public List<Menu> getMenuByRole2(String id) {
        return menuMapper.getMenuByRole2(id);
    }


}
