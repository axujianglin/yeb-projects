package com.xu.server.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.xu.server.pojo.Department;
import com.xu.server.pojo.RespBean;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface IDepartmentService extends IService<Department> {

    /**
     * 获取所有的管理部门
     * @return
     */
    List<Department> getAllDepart();

    /**
     * 添加管理部门
     * @param dept
     * @return
     */
    RespBean addDept(Department dept);

    /**
     * 删除管理部门
     * @param id
     * @return
     */
    RespBean delDept(Integer id);
}
