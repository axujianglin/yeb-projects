package com.xu.server.controller;


import com.xu.server.pojo.Department;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IDepartmentService;
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
@RequestMapping("system/basic/department")
public class DepartmentController {

    @Autowired
    private IDepartmentService departmentService;

    @ApiOperation("获取所有管理部门")
    @GetMapping("/getAllDepart")
    public List<Department> getAllDepart(){
        return departmentService.getAllDepart();
    }

    @ApiOperation("添加管理部门")
    @PostMapping("/addDep")
    public RespBean addDep(Department dept){
        return departmentService.addDept(dept);
    }

    @ApiOperation("删除管理部门")
    @DeleteMapping("/delDept")
    public RespBean delDep(Integer id){
        return departmentService.delDept(id);
    }


}
