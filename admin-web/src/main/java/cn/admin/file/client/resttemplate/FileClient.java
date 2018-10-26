package com.hzhetun.example.client.resttemplate;

import com.hzhetun.example.utils.RestfulResult;
import com.hzhetun.example.utils.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *aliyun upload class
 * @author cbk
 * @date 2017/12/13
 */

@Service
public class FileClient {
    private Logger logger= Logger.getLogger(FileClient.class);

    @Autowired
    private RestTemplate restTemplate;

    private static final String UPLOAD_URL="http://FILESERVICE/file/upload";

    private static final String IMG_URL="http://FILESERVICE/file/getImgUrl";

    @Autowired
    private ServletContext servletContext;

    public RestfulResult fileUploadService(MultipartFile multipartFile, Integer uid){
        HttpHeaders headers=new HttpHeaders();
        MediaType mediaType=MediaType.parseMediaType(MediaType.MULTIPART_FORM_DATA_VALUE);
        headers.setContentType(mediaType);
        MultiValueMap<String,Object> multiValueMap=new LinkedMultiValueMap<>();
        String tempFileName= StringUtil.getUUID()+multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        String absulotionPath="";
        try {
            absulotionPath = WebUtils.getRealPath(servletContext,"");
        }catch (FileNotFoundException e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }
        logger.info("项目的绝对路径:"+absulotionPath);
        String tempPath=absulotionPath+tempFileName;
        File file=new File(tempPath);
        try {
            multipartFile.transferTo(file);
        }catch (IOException e){
            e.printStackTrace();
            logger.info(e.getMessage());
        }

        FileSystemResource fileSystemResource=new FileSystemResource(file);
        multiValueMap.add("file",fileSystemResource);
        multiValueMap.add("uid",uid);
        multiValueMap.add("originFileName",multipartFile.getOriginalFilename());
        HttpEntity<MultiValueMap<String,Object>> httpEntity=new HttpEntity<>(multiValueMap,headers);
        return restTemplate.postForObject(UPLOAD_URL,httpEntity,RestfulResult.class);
    }

    @HystrixCommand(fallbackMethod = "getImgUrlError")
    public RestfulResult getImgUrl(Integer imgId){
        return restTemplate.getForObject(IMG_URL+"?imgId="+imgId,RestfulResult.class);
    }

    public RestfulResult getImgUrlError(Integer imgId){
        return RestfulResult.error500("服务不可用");
    }
}
