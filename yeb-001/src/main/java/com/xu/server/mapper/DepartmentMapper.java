package com.xu.server.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.xu.server.pojo.Department;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author 许江林
 * @since 2021-11-23
 */
public interface DepartmentMapper extends BaseMapper<Department> {

    /**
     * 获取所有的管理部门
     * @return
     */
    List<Department> getAllDepart(Integer parentId);

    /**
     * 添加管理部门
     * @param dept
     * @return
     */
    void addDept(Department dept);

    /**
     * 删除管理部门
     * @param id
     * @return
     */
    void delDept(Integer id);
}
