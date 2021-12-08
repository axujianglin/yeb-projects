package com.xu.server.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.xu.server.mapper.DepartmentMapper;
import com.xu.server.pojo.Department;
import com.xu.server.pojo.RespBean;
import com.xu.server.service.IDepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

    @Autowired
    private DepartmentMapper departmentMapper;

    /**
     * 获取所有的管理部门
     * @return
     */
    @Override
    public List<Department> getAllDepart() {

        return departmentMapper.getAllDepart(-1);
    }

    /**
     * 添加管理部门
     * @param dept
     * @return
     */
    @Override
    public RespBean addDept(Department dept) {
        dept.setEnabled(true);
        departmentMapper.addDept(dept);
        if(1 == dept.getResult()){
            //添加成功
            return RespBean.success("添加成功",dept);
        }
        return RespBean.error("添加失败");
    }

    /**
     * 删除管理部门
     * @param id
     * @return
     */
    @Override
    public RespBean delDept(Integer id) {
        Department department = new Department();
        department.setId(id);
        departmentMapper.delDept(id);
        if(department.getResult()==-2){
            return RespBean.error("此部门下有子部门，删除失败");
        }
        else if(department.getResult()==-1){
            return RespBean.error("此部门下有员工，删除失败");
        }
        else if(department.getResult()==1){
            return RespBean.success("删除成功",department);
        }
        return RespBean.error("删除失败");
    }
}
