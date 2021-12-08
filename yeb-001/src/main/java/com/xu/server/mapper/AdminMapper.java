package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.Admin;
import com.xu.server.pojo.RespBean;
import com.xu.server.pojo.Role;
import org.apache.ibatis.annotations.Param;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface AdminMapper extends BaseMapper<Admin> {

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(@Param("id") Integer id, @Param("keywords") String keywords);
}
