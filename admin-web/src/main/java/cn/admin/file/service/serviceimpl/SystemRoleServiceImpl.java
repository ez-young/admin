package cn.admin.file.service.serviceimpl;

import cn.admin.file.pojo.SystemRole;
import cn.admin.file.service.SystemRoleService;
import cn.admin.file.utils.StringUtil;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import cn.admin.file.mapper.SystemRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "roles")
public class SystemRoleServiceImpl implements SystemRoleService {
    @Autowired
    private SystemRoleMapper systemRoleMapper;

    @Override
    public Page<SystemRole> findRoleList(SystemRole systemRole, int page, int limit) {
        Page<SystemRole> pages=new Page<SystemRole>();
        EntityWrapper<SystemRole> ew=new EntityWrapper<SystemRole>();
        if(StringUtil.isNotBlank(systemRole.getName())){
            ew.like("name",systemRole.getName());
        }
        ew.orderBy("weight");
        List<SystemRole> systemRoles=systemRoleMapper.selectPage(new Page<SystemRole>(page,limit),ew);
        int total=systemRoleMapper.selectCount(ew);
        pages.setRecords(systemRoles);
        pages.setTotal(total);
        return pages;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "'findRoleSelectList'")
    public Integer saveOrUpdate(SystemRole systemRole) {
        if(systemRole.getId()==null){
            return systemRoleMapper.insert(systemRole);
        }
        else{
            return systemRoleMapper.updateById(systemRole);
        }
    }

    @Override
    public SystemRole findOne(int id) {
        return systemRoleMapper.selectById(id);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(key = "'findRoleSelectList'")
    public void del(String ids) {
        String[] id=ids.split(",");
        for(String s:id){
            systemRoleMapper.deleteById(Integer.parseInt(s));
        }
    }

    @Override
    @Cacheable(key = "'findRoleSelectList'")
    public List<SystemRole> findRoleSelectList() {
        return systemRoleMapper.selectList(new EntityWrapper<SystemRole>());
    }
}
