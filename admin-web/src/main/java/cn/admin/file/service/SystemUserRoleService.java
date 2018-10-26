package com.hzhetun.example.service;

import com.hzhetun.example.pojo.SystemUserRole;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */

public interface SystemUserRoleService {
    /**
     * 查询某个用户对应的角色的方法
     * @param userId
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findByUser(int userId);

    /**
     * 删除用户角色对应关系的方法
     * @param userId
     */
    void deleteByUserId(int userId);

    /**
     * 保存用户角色对应关系的方法
     * @param systemUserRole
     */
    void save(SystemUserRole systemUserRole);
}
