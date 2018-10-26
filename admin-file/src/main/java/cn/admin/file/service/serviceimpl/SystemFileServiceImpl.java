package cn.admin.file.service.serviceimpl;

import cn.admin.file.mapper.SystemFileMapper;
import cn.admin.file.service.SystemFileService;
import cn.admin.file.pojo.SystemFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "files")
public class SystemFileServiceImpl implements SystemFileService {
    @Autowired
    private SystemFileMapper systemFileMapper;

    @Override
    public int save(SystemFile systemFile) {
        systemFileMapper.insertSelective(systemFile);
        return systemFile.getId();
    }

    @Override
    @Cacheable(key = "'findOne'+#p0")
    public SystemFile findOne(Integer id) {
        return systemFileMapper.selectByPrimaryKey(id);
    }
}
