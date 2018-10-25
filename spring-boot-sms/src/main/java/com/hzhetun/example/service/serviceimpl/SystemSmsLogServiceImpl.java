package com.hzhetun.example.service.serviceimpl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.hzhetun.example.mapper.SystemSmsLogMapper;
import com.hzhetun.example.pojo.SystemSmsLog;
import com.hzhetun.example.service.SystemSmsLogService;
import com.hzhetun.example.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


/**
 * @author 陈炳坤
 * @date 2018/01/09
 */
@Service
public class SystemSmsLogServiceImpl implements SystemSmsLogService{
    @Autowired
    private SystemSmsLogMapper systemSmsLogMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public String save(String[] mobiles,String content) {
        String token= StringUtil.getUUID();

        for(String mobile:mobiles){
            SystemSmsLog systemSmsLog=new SystemSmsLog();
            systemSmsLog.setMobile(mobile);
            systemSmsLog.setContent(content);
            systemSmsLog.setApi("LUOSIMAO");
            systemSmsLog.setToken(token);
            systemSmsLog.setStatus(0);
            systemSmsLog.setCreateTime(new Date());
            systemSmsLogMapper.insert(systemSmsLog);
        }

        return token;
    }

    @Override
    public List<SystemSmsLog> findAllUnSend() {
        return systemSmsLogMapper.selectList(new EntityWrapper<SystemSmsLog>().eq("status",0));
    }

    @Override
    public void update(SystemSmsLog systemSmsLog) {
        systemSmsLogMapper.updateById(systemSmsLog);
    }
}
