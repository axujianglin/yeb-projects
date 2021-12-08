package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.AdminRole;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface AdminRoleMapper extends BaseMapper<AdminRole> {

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */
    Integer updateRole(@Param("adminId") Integer adminId, @Param("rids") Integer[] rids);
}
