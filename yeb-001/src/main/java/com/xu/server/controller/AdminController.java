package com.xu.server.controller;


import com.xu.server.pojo.Admin;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IAdminService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
@RestController
@RequestMapping("/system/admin")
public class AdminController {

    @Autowired
    private IAdminService adminService;

    @ApiOperation("获取所有操作员")
    @GetMapping("/getAll")
    public List<Admin> getAllAdmins(String keywords){
        return adminService.getAllAdmins(keywords);
    }

    @ApiOperation("更新操作员")
    @PutMapping("/update")
    public RespBean updateAdmin(@RequestBody Admin admin){
        if (adminService.updateById(admin)) {
            return RespBean.success("更新成功");
        }
        return RespBean.error("更新失败");
    }

    @ApiOperation("更新操作员的角色")
    @PutMapping("/updateRole")
    public RespBean updateRole(Integer adminId,Integer[] rids){
        return adminService.updateRole(adminId,rids);
    }


}
