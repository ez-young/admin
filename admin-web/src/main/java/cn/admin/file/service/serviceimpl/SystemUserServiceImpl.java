package cn.admin.file.service.serviceimpl;

import cn.admin.file.pojo.SystemUser;
import cn.admin.file.pojo.SystemUserRole;
import cn.admin.file.utils.StringUtil;
import cn.admin.file.mapper.SystemUserMapper;
import cn.admin.file.service.SystemUserRoleService;
import cn.admin.file.service.SystemUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "user")
public class SystemUserServiceImpl implements SystemUserService {
	@Autowired
	private SystemUserMapper systemUserMapper;

	@Autowired
	private SystemUserRoleService systemUserRoleService;

	@Override
    public SystemUser login(SystemUser systemUser) {
		return systemUserMapper.getSystemUser(systemUser);
	}

	@Override
    public List<SystemUser> findUserList(SystemUser systemUser, int page, int limit){
		Map<String,Object> map=new HashMap<String,Object>(16);
		map.put("username", StringUtil.isBlank(systemUser.getUsername())?null:"%"+systemUser.getUsername()+"%");
		map.put("tel",StringUtil.isBlank(systemUser.getTel())?null:"%"+systemUser.getTel()+"%");
		map.put("realname",StringUtil.isBlank(systemUser.getRealname())?null:"%"+systemUser.getRealname()+"%");
		map.put("birthdate",systemUser.getBirthdate());
		map.put("status",systemUser.getStatus());
		map.put("offset",(page-1)*limit);
		map.put("rows",limit);
		List<SystemUser> users=systemUserMapper.findUserList(map);
		for(SystemUser user:users){
			List<Map<String,Object>> rolenames=systemUserRoleService.findByUser(user.getId());
			StringBuffer rolesNames=new StringBuffer();
			if(rolenames.size()>0){
				for(int i=0;i<rolenames.size();i++){
					Map<String,Object> role=rolenames.get(i);
					if(i==(rolenames.size()-1)){
						rolesNames.append((String)role.get("name"));
					}
					else{
						rolesNames.append((String)role.get("name")+",");
					}
				}
			}
			user.setRolenames(rolesNames.toString());
		}

		return users;
	}

	@Override
    public Long count(SystemUser systemUser) {
		Map<String,Object> map=new HashMap<String,Object>(16);
		map.put("username", StringUtil.isBlank(systemUser.getUsername())?null:"%"+systemUser.getUsername()+"%");
		map.put("tel",StringUtil.isBlank(systemUser.getTel())?null:"%"+systemUser.getTel()+"%");
		map.put("realname",StringUtil.isBlank(systemUser.getRealname())?null:"%"+systemUser.getRealname()+"%");
		map.put("birthdate",systemUser.getBirthdate());
		map.put("status",systemUser.getStatus());
		return systemUserMapper.count(map);
	}

	@Override
    @Transactional(rollbackFor = Exception.class)
	@CacheEvict(key = "'findOne'+#p0.id",condition = "#p0.id!=null")
	public void saveOrUpdate(SystemUser systemUser,String[] roles){
		int userId=0;
		//新增
		if(systemUser.getId()==null){
			systemUser.setCreateTime(new Date());
			systemUser.setUpdateTime(new Date());
			systemUserMapper.insertSelective(systemUser);
			userId=systemUser.getId();
		}
		else{
		    //修改
			systemUser.setUpdateTime(new Date());
			systemUserMapper.updateByPrimaryKeySelective(systemUser);

			userId=systemUser.getId();
			//删除原有的关系
			systemUserRoleService.deleteByUserId(userId);
		}

		for(String role:roles){
			SystemUserRole systemUserRole=new SystemUserRole();
			systemUserRole.setUserId(userId);
			systemUserRole.setRoleId(Integer.parseInt(role));
			systemUserRoleService.save(systemUserRole);
		}
	}

	@Override
	@CacheEvict(key = "'findOne'+#p0.id",condition = "#p0.id!=null")
    public void saveOrUpdate(SystemUser systemUser) {
        //新增
		if(systemUser.getId()==null){
			systemUser.setCreateTime(new Date());
			systemUser.setUpdateTime(new Date());
			systemUserMapper.insertSelective(systemUser);
		}
		else{
		    //修改
			systemUser.setUpdateTime(new Date());
			systemUserMapper.updateByPrimaryKeySelective(systemUser);
		}
	}

	@Override
	@Cacheable(key = "'findOne'+#p0")
    public SystemUser findOne(int id){
		return systemUserMapper.selectByPrimaryKey(id);
	}

	@Override
	@CacheEvict(key = "'findOne'+#p0")
    public int del(int id) {
		return systemUserMapper.deleteByPrimaryKey(id);
	}

	@Override
    public SystemUser findByCondition(SystemUser systemUser) {
		return systemUserMapper.findByCondition(systemUser);
	}

	@Override
	@CacheEvict(allEntries = true)
    public int del() {
		return 0;
	}
}
