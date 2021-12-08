package com.xu.server.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.mapper.MenuRoleMapper;
import com.xu.server.pojo.MenuRole;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IMenuRoleService;
import org.apache.ibatis.annotations.Param;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

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
public class MenuRoleServiceImpl extends ServiceImpl<MenuRoleMapper, MenuRole> implements IMenuRoleService {
    @Autowired
    private MenuRoleMapper menuRoleMapper;

    /**
     * 批量增加菜单权限
     * @param rid
     * @param mids
     */
    @Override
    @Transactional
    public RespBean updateMenuRole(Integer rid,Integer[] mids) {
        menuRoleMapper.delete(new QueryWrapper<MenuRole>().eq("rid",rid));
        if(mids == null || mids.length==0){
            return RespBean.success("更新成功");
        }
        menuRoleMapper.insertRecord(rid,mids);
        return RespBean.success("更新成功");
    }
}
