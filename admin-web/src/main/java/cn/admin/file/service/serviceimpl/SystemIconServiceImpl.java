package cn.admin.file.service.serviceimpl;

import cn.admin.file.pojo.SystemIcon;
import cn.admin.file.service.SystemIconService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import cn.admin.file.mapper.SystemIconMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "icons")
public class SystemIconServiceImpl implements SystemIconService {
    @Autowired
    private SystemIconMapper systemIconMapper;

    @Override
    @Cacheable(key = "'selectList'")
    public List<SystemIcon> selectList(){
        return systemIconMapper.selectList(new EntityWrapper<SystemIcon>().orderBy("weight"));
    }
}
