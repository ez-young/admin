package com.hzhetun.example.client.feign;

import com.hzhetun.example.hystrix.LuosimaoHystrix;
import com.hzhetun.example.utils.RestfulResult;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author 陈炳坤
 * @date 2018/01/15
 */
@FeignClient(value = "SMSSERVICE",fallback = LuosimaoHystrix.class)
public interface LuosimaoClient {
    /**
     * 发送短信
     * @param mobiles 手机号,多个以逗号隔开
     * @param content 发送内容
     * @return RestfulResult 返回的类型
     */
    @GetMapping(value = "/sms/sendSms")
    RestfulResult sendSms(@RequestParam(name = "mobiles") String mobiles,@RequestParam(name = "content") String content);
}
