package com.xu.server.controller;

import com.xu.server.pojo.Admin;
import com.xu.server.pojo.AdminLoginParam;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IAdminService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

/**
 * 登录控制器
 */
@Api(tags = "LoginController")
@RestController
public class LoginController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation(value ="登录之后返回token")
    @PostMapping("/login")
    public RespBean login(AdminLoginParam param, HttpServletRequest request){
        return adminService.login(param,request);
    }

    @ApiOperation("根据姓名获取用户信息")
    @GetMapping("/admin/info")
    public Admin getAdminInfo(Principal principal){
        if(null == principal){
            return null;
        }
        String username = principal.getName();
        Admin admin = adminService.getAdminByUserName(username);
        admin.setPassword(null);
        //将查询到的角色列表放到用户中，即获取权限
        admin.setRoles(adminService.getRoles(admin.getId()));
        return admin;
    }

    @ApiOperation("退出登录")
    @GetMapping("/logout")
    public RespBean logout(){
        return RespBean.success("注销成功");
    }
}
