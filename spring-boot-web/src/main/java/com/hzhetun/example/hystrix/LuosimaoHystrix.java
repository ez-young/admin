package com.hzhetun.example.hystrix;

import com.hzhetun.example.client.feign.LuosimaoClient;
import com.hzhetun.example.utils.RestfulResult;
import org.springframework.stereotype.Component;

/**
 * luosimao服务宕机回退的方法
 * @author 陈炳坤
 * @date 2018/01/16
 */
@Component
public class LuosimaoHystrix implements LuosimaoClient{
    @Override
    public RestfulResult sendSms(String mobiles, String content) {
        return RestfulResult.error500("短信服务不可用");
    }
}
