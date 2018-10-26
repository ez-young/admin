package cn.admin.file.task;

import cn.admin.file.api.LuosimaoApi;
import cn.admin.file.pojo.SystemSmsLog;
import cn.admin.file.service.SystemSmsLogService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author 陈炳坤
 * @date 2018/01/10
 */
@Component
public class SmsTask {
    private SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    private Logger logger=Logger.getLogger(this.getClass());

    private Integer count0=1;

    @Autowired
    private SystemSmsLogService systemSmsLogService;

    @Autowired
    private LuosimaoApi luosimaoApi;

    @Scheduled(fixedRate = 5000)
    @Transactional(rollbackFor = Exception.class)
    public void sendSms(){
        logger.info("===第"+(count0++)+"次执行"+",执行时间:"+simpleDateFormat.format(new Date()));
        long start=System.currentTimeMillis();
        List<SystemSmsLog> systemSmsLogs=systemSmsLogService.findAllUnSend();
        if(systemSmsLogs.size()>0){
            for(SystemSmsLog systemSmsLog:systemSmsLogs){
                //发送短信
                String response=luosimaoApi.send(systemSmsLog.getMobile(),systemSmsLog.getContent());
                systemSmsLog.setResponse(response);
                systemSmsLog.setStatus(1);
                systemSmsLog.setUpdateTime(new Date());
                systemSmsLogService.update(systemSmsLog);
            }
        }
        long end=System.currentTimeMillis();
        logger.info("短信发送执行时间:"+(end-start)+"ms");
    }
}
