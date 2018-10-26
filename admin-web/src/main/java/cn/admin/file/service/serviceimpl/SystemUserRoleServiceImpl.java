package cn.admin.file.service.serviceimpl;

import cn.admin.file.pojo.SystemUserRole;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.admin.file.mapper.SystemUserRoleMapper;
import cn.admin.file.service.SystemUserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "userrole")
public class SystemUserRoleServiceImpl implements SystemUserRoleService {
    @Autowired
    private SystemUserRoleMapper systemUserRoleMapper;

    @Override
    @Cacheable(key = "'findByUser'+#p0")
    public List<Map<String, Object>> findByUser(int userId) {
        return systemUserRoleMapper.findByUserId(userId);
    }

    @Override
    @CacheEvict(key = "'findByUser'+#p0")
    public void deleteByUserId(int userId) {
        systemUserRoleMapper.delete(new EntityWrapper<SystemUserRole>().eq("user_id",userId));
    }

    @Override
    @CacheEvict(key = "'findByUser'+#p0.userId")
    public void save(SystemUserRole systemUserRole) {
        systemUserRoleMapper.insert(systemUserRole);
    }
}
