package com.xu.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.server.pojo.Admin;
import com.xu.server.pojo.AdminLoginParam;
import com.xu.server.pojo.RespBean;
import com.xu.server.pojo.Role;
import org.springframework.security.core.userdetails.UserDetails;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface IAdminService extends IService<Admin> {
    /**
     * 根据用户id获取角色列表
     * @param adminId
     * @return
     */
    List<Role> getRoles(Integer adminId);


    UserDetails getUserByUserName(String username);

    RespBean login(AdminLoginParam param, HttpServletRequest request);

    Admin getAdminByUserName(String username);

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    List<Admin> getAllAdmins(String keywords);

    /**
     * 更新操作员的角色
     * @param adminId
     * @param rids
     * @return
     */
    RespBean updateRole(Integer adminId, Integer[] rids);
}
