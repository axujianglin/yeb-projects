package com.xu.server.controller;


import com.xu.server.pojo.Position;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IPositionService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
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
@RequestMapping("/system/basic/pos")
public class PositionController {
    @Autowired
    private IPositionService positionService;

    @ApiOperation(value = "获取所有职位信息")
    @GetMapping("/")
    public List<Position> getAllPosition(){
        return positionService.list();
    }

    @ApiOperation(value = "添加职位信息")
    @PostMapping("/")
    public RespBean addPosition(@RequestBody Position position){
        if (positionService.save(position)) {
            return RespBean.success("添加职位成功");
        }
        return RespBean.error("添加职位失败");
    }

    @ApiOperation(value = "更新职位信息")
    @PutMapping("/")
    public RespBean updatePosition(@RequestBody Position position){
        if (positionService.updateById(position)) {
            return RespBean.success("更新职位成功");
        }
        return RespBean.error("更新职位失败");
    }
    @ApiOperation(value = "删除职位信息")
    @DeleteMapping("/{id}")
    public RespBean deletePosition(@PathVariable Integer id){
        if(positionService.removeById(id)){
            return RespBean.success("删除职位成功");
        }
        return RespBean.success("删除职位失败");
    }
    @ApiOperation(value = "批量删除职位信息")
    @DeleteMapping("/")
    public RespBean deletePositions(String[] ids){
        if(positionService.removeByIds(Arrays.asList(ids))){
            return RespBean.success("删除职位成功");
        }
        return RespBean.success("删除职位失败");
    }




}
