package com.xu.server.pojo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "显示所有菜单")
public class Roles {

    @ApiModelProperty(value = "一级菜单id")
    private Integer id1;
    @ApiModelProperty(value = "一级菜单name")
    private String name1;
    @ApiModelProperty(value = "二级菜单id")
    private Integer id2;
    @ApiModelProperty(value = "二级菜单name")
    private String name2;
    @ApiModelProperty(value = "三级菜单id")
    private Integer id3;
    @ApiModelProperty(value = "三级菜单name")
    private String name3;
}
