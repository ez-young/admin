package com.hzhetun.example.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.hzhetun.example.pojo.SystemRole;

import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
public interface SystemRoleService {
    /**
     * 角色列表分页查询的方法
     * @param systemRole
     * @param page
     * @param limit
     * @return Page<SystemRole>
     */
    Page<SystemRole> findRoleList(SystemRole systemRole, int page, int limit);

    /**
     * 角色保存修改的方法
     * @param systemRole
     * @return Integer
     */
    Integer saveOrUpdate(SystemRole systemRole);

    /**
     * 根据主键查询的方法
     * @param id
     * @return SystemRole
     */
    SystemRole findOne(int id);

    /**
     * 删除方法
     * @param ids
     */
    void del(String ids);

    /**
     * 角色下拉列表的方法
     * @return List<SystemRole>
     */
    List<SystemRole> findRoleSelectList();
}
