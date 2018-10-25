package com.hzhetun.example.service;

import com.hzhetun.example.pojo.SystemSmsLog;

import java.util.List;

/**
 * @author 陈炳坤
 * @date 2018/01/09
 */
public interface SystemSmsLogService {
    /**
     * 短信发送记录保存
     * @param mobiles
     * @param content
     * @return
     */
    String save(String[] mobiles,String content);

    /**
     * 获得所有未发送的短信记录
     * @return
     */
    List<SystemSmsLog> findAllUnSend();

    /**
     * 更新方法
     * @param systemSmsLog
     */
    void update(SystemSmsLog systemSmsLog);
}
