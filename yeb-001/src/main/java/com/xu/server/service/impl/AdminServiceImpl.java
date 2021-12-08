package com.xu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.config.security.JwtTokenUtils;
import com.xu.server.mapper.AdminMapper;
import com.xu.server.mapper.AdminRoleMapper;
import com.xu.server.mapper.RoleMapper;
import com.xu.server.pojo.*;
import com.xu.server.service.IAdminService;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private JwtTokenUtils jwtTokenUtil;
    @Value("${jwt.tokenHead}")
    private String tokenHead;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private AdminRoleMapper adminRoleMapper;

    /**
     * 根据用户id获取角色列表
     * @param adminId
     * @return
     */
    @Override
    public List<Role> getRoles(Integer adminId) {
        return roleMapper.getRoleByAdmin(adminId);
    }

    @Override
    public UserDetails getUserByUserName(String username) {
        return null;
    }

    @Override
    public RespBean login(AdminLoginParam param, HttpServletRequest request) {
        UserDetails userDetails =
                userDetailsService.loadUserByUsername(param.getUsername());
        if (null == userDetails || !passwordEncoder.matches(param.getPassword(),
                userDetails.getPassword())) {
            return RespBean.error("用户名或密码不正确!");
        }
        if (!userDetails.isEnabled()){
            return RespBean.error("账号被禁用，请联系管理员!");
        }
        if(param.getCode() == null || !param.getCode().equals(request.getSession().getAttribute("captcha"))){
            return RespBean.error("验证码错误，请重新输入验证码");
        }
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null,
                        userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtTokenUtil.generateToken(userDetails);

        Map<String, String> tokenMap = new HashMap<>();
        tokenMap.put("token", token);
        tokenMap.put("tokenHead", tokenHead);
        return RespBean.success("登录成功", tokenMap);
    }

    /**
     * 根据用户名获取用户
     *
     * @param username
     * @return
     */
    @Override
    public Admin getAdminByUserName(String username) {
        return adminMapper.selectOne(new QueryWrapper<Admin>().eq("username",
                username));
    }

    /**
     * 获取所有操作员
     * @param keywords
     * @return
     */
    @Override
    public List<Admin> getAllAdmins(String keywords) {
        return adminMapper.getAllAdmins(((Admin) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId(),keywords);

    }

    /**
     * 更新操作员的角色
     * @param adminId
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public RespBean updateRole(Integer adminId, Integer[] rids) {
        adminRoleMapper.delete(new QueryWrapper<AdminRole>().eq("adminId",adminId));
        Integer integer = adminRoleMapper.updateRole(adminId, rids);
        if(integer == rids.length){
            return RespBean.success("更新角色成功");
        }
        return RespBean.error("更新角色失败");
    }


}
