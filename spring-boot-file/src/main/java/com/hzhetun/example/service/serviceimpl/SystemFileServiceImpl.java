package com.hzhetun.example.service.serviceimpl;

import com.hzhetun.example.mapper.SystemFileMapper;
import com.hzhetun.example.pojo.SystemFile;
import com.hzhetun.example.service.SystemFileService;
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
