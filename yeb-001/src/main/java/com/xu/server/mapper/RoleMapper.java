package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.Role;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface RoleMapper extends BaseMapper<Role> {

    List<Role> getRoleByAdmin(Integer adminId);

}
