package cn.admin.file.client.resttemplate;

import cn.admin.file.utils.RestfulResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
public class LuosimaoClient {
    @Autowired
    private RestTemplate restTemplate;

    public static final String SEND_URL="http://SMSSERVICE/sms/sendSms?mobiles={mobiles}&content={content}";

    public RestfulResult sendSms(String mobiles, String content){
        RestfulResult restfulResult=restTemplate.getForObject(SEND_URL,RestfulResult.class,mobiles,content);
        return restfulResult;
    }
}
