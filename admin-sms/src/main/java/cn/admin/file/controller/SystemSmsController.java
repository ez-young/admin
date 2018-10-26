package cn.admin.file.controller;

import cn.admin.file.service.SystemSmsLogService;
import cn.admin.file.utils.RestfulResult;
import cn.admin.file.utils.StringUtil;
import cn.admin.file.utils.ValidationUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author cbk
 * @date 2018/01/07
 */
@RestController
@RequestMapping(value = "sms")
public class SystemSmsController {
    private Logger logger=Logger.getLogger(this.getClass());

    public static final String SPLIT_STRING=".";

    @Autowired
    private SystemSmsLogService systemSmsLogService;

    @RequestMapping(value = "sendSms")
    public RestfulResult smsSend(@RequestParam String mobiles, @RequestParam String content){
        logger.info("测试短信调用服务");
        if(StringUtil.isBlank(mobiles)){
            return RestfulResult.warning(-10,"手机号不能为空");
        }

        String[] mobile=mobiles.split(",");
        for(String m:mobile){
            if(!ValidationUtil.checkIsMobile(m)){
                return RestfulResult.warning(-20,"请输入正确的手机号");
            }
        }

        if(StringUtil.isBlank(content)){
            return RestfulResult.warning(-30,"发送内容不能为空");
        }

        String token=systemSmsLogService.save(mobile,content);

        return new RestfulResult(200,"发送成功","token",token);
    }
}
