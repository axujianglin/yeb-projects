package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.MenuRole;
import org.apache.ibatis.annotations.Param;


/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface MenuRoleMapper extends BaseMapper<MenuRole> {

    /**
     * 批量增加菜单权限
     * @param rid
     * @param mids
     */
    Integer insertRecord(@Param("rid")Integer rid, @Param("mids")Integer[] mids);
}
