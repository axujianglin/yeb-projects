package com.xu.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.server.pojo.MenuRole;
import com.xu.server.pojo.RespBean;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface IMenuRoleService extends IService<MenuRole> {

    /**
     * 更新角色菜单
     * @param rid
     * @param mid
     * @return
     */
    RespBean updateMenuRole(Integer rid, Integer[] mid);

}
