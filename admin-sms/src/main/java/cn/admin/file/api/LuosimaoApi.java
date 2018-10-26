package cn.admin.file.api;

import cn.admin.file.mapper.SystemSmsApiMapper;
import cn.admin.file.pojo.SystemSmsApi;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;
import java.net.URI;
import java.net.URISyntaxException;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
public class LuosimaoApi {
    private static final String SEND_URL="http://sms-api.luosimao.com/v1/send.json";
//    private static final String send_batch_url="http://sms-api.luosimao.com/v1/send_batch.json";

    @Autowired
    private SystemSmsApiMapper systemSmsApiMapper;

    private Logger logger= Logger.getLogger(LuosimaoApi.class);

    @Cacheable(value = "api",key = "'getApiInfoLuosimaoApi'")
    public SystemSmsApi getApiInfo(){
        SystemSmsApi smsApiEntity=systemSmsApiMapper.findByApiName("LuosimaoApi");
        return smsApiEntity;
    }

    /**
     * 单条发送
     * @param mobile
     * @param messgae
     * @return
     */
    public String send(String mobile,String messgae){
        SystemSmsApi apiInfo=getApiInfo();

        Client client=Client.create();
        client.addFilter(new HTTPBasicAuthFilter("api","key-"+apiInfo.getApiKey()));
        URI uri=null;
        try {
            uri=new URI(SEND_URL);
        }catch (URISyntaxException e){
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        WebResource webResource=client.resource(uri);
        MultivaluedMapImpl formData=new MultivaluedMapImpl();
        messgae += apiInfo.getApiSignature();
        formData.add("mobile",mobile);
        formData.add("message",messgae);
        ClientResponse response=webResource.type(MediaType.APPLICATION_FORM_URLENCODED).post(ClientResponse.class,formData);
        String responseString=response.getEntity(String.class);
        logger.info("response:"+responseString);
        return responseString;
    }
}
