package com.hzhetun.example.controller.system;

import com.hzhetun.example.client.feign.LuosimaoClient;
import com.hzhetun.example.utils.RestfulResult;
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
    @Autowired
    private LuosimaoClient luosimaoClient;

    @RequestMapping(value = "sendSms")
    public RestfulResult sendSms(@RequestParam String mobiles,@RequestParam String content){
        return luosimaoClient.sendSms(mobiles, content);
    }
}
