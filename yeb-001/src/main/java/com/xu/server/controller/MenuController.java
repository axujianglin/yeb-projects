package com.xu.server.controller;


import com.xu.server.pojo.Menu;
import com.xu.server.service.IMenuService;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RequestMapping("/system/cfg")
public class MenuController {

    @Autowired
    IMenuService menuService;

    @ApiOperation("根据用户id获取菜单列表")
    @GetMapping("/menu")
    public List<Menu> getMenuByAdminId(){
        return menuService.getMenuByAdminId();
    }
}
