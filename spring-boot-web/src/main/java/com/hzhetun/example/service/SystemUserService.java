package com.hzhetun.example.service;


import com.hzhetun.example.pojo.SystemUser;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
public interface SystemUserService {
	/**
	 * 登陆方法
	 * @param systemUser
	 * @return SystemUser
	 */
	SystemUser login(SystemUser systemUser);

	/**
	 * 列表查询方法
	 * @param systemUser
	 * @param page
	 * @param limit
	 * @return List<SystemUser>
	 */
	List<SystemUser> findUserList(SystemUser systemUser, int page, int limit);

	/**
	 * 记录总数查询方法
	 * @param systemUser
	 * @return Long
	 */
	Long count(SystemUser systemUser);

	/**
	 * 保存或者修改方法
	 * @param systemUser
	 * @param roles
	 */
	void saveOrUpdate(SystemUser systemUser, String[] roles);

	/**
	 * 保存或者修改方法
	 * @param systemUser
	 */
	void saveOrUpdate(SystemUser systemUser);

	/**
	 * 根据主键查询方法
	 * @param id
	 * @return SystemUser
	 */
	SystemUser findOne(int id);

	/**
	 * 删除方法
	 * @param id
	 * @return int
	 */
	int del(int id);

	/**
	 * 根据条件动态查询方法
	 * @param systemUser
	 * @return SystemUser
	 */
	SystemUser findByCondition(SystemUser systemUser);

	/**
	 * 删除方法
	 * @return int
	 */
	int del();
}
