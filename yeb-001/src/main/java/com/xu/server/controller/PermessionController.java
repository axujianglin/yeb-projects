package com.xu.server.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.xu.server.pojo.*;
import com.xu.server.service.IMenuRoleService;
import com.xu.server.service.IMenuService;
import com.xu.server.service.IRoleService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 权限组操作
 */
@RestController
@RequestMapping("/system/basic/per")
public class PermessionController {

    @Autowired
    private IRoleService roleService;
    @Autowired
    private IMenuService menuService;
    @Autowired
    private IMenuRoleService menuRoleService;

    /**
     * 添加角色
     * @param role
     * @return
     */
    @PostMapping("/add")
    @ApiOperation(value = "添加角色")
    public RespBean addRole(Role role){
        if(!role.getName().startsWith("ROLE_")){
            role.setName("ROLE_"+role.getName());
        }
        if(roleService.save(role)){
            return RespBean.success("角色添加成功");
        }
        return RespBean.error("角色添加失败");
    }

    /**
     *删除单个角色
     * @param id
     * @return
     */
    @DeleteMapping("/del/{id}")
    @ApiOperation(value = "删除单个角色")
    public RespBean deleteRole(@PathVariable Integer id){
        if (roleService.removeById(id)) {
            return RespBean.success("删除角色成功");
        }
        return RespBean.error("删除角色失败");
    }

    /**
     * 更新一个角色
     * @param role
     * @return
     */
    @PutMapping("/update")
    @ApiOperation(value = "更新角色")
    public RespBean updateRole(Role role){
        if (roleService.updateById(role)) {
            return RespBean.success("更新角色成功");
        }
        return RespBean.error("更新角色失败");
    }

    /**
     * 查询所有角色
     * @return
     */
    @GetMapping("/query")
    @ApiOperation(value = "查询所有角色")
    public List<Role> queryRole(){
        return roleService.list();
    }

    /**
     * 删除多个角色
     * @param ids
     * @return
     */
    @DeleteMapping("/deletes")
    @ApiOperation(value = "删除多个角色")
    public RespBean deleteRoles(String[] ids){
        if (roleService.removeByIds(Arrays.asList(ids))) {
            RespBean.success("删除多个角色成功");
        }
        return RespBean.error("删除多个角色失败");
    }

    /**
     * 查询所有菜单
     * @return
     */
    @GetMapping("/queryMenu")
    @ApiOperation(value = "查询所有菜单")
    public List<Menu> queryMenu(){
        return menuService.getAllMenus();
    }

    /**
     * 查询特定角色对应的菜单
     * @param id
     * @return
     */
    @GetMapping("/querySpe/{id}")
    @ApiOperation("根据特定用户查找菜单")
    public List<Integer> queryMenuSpe(@PathVariable String id){
        return menuRoleService.list(new QueryWrapper<MenuRole>().eq("rid", id)).stream()
                .map(MenuRole::getMid).collect(Collectors.toList());


    }
    @GetMapping("/updateMenu")
    @ApiOperation("更新菜单角色")
    public RespBean updateMenuRole(Integer rid,Integer[] mids){
        return menuRoleService.updateMenuRole(rid,mids);
    }

}
