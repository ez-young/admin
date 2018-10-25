package com.hzhetun.example.api;

import com.aliyun.oss.OSSClient;
import com.hzhetun.example.pojo.SystemFile;
import com.hzhetun.example.service.SystemFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

/**
 *aliyun upload class
 * @author cbk
 * @date 2017/12/13
 */

@Service
public class FileUploadApi {

    private Logger logger= Logger.getLogger(FileUploadApi.class);

    /**
     * 访问域名
     */
    @Value("${oss.endpoint}")
    private String endpoint;

    @Value("${oss.bucket}")
    private String bucket;

    /**
     * 标识用户
     */
    @Value("${oss.accessKeyId}")
    private String accessKeyId;

    /**
     * 秘钥
     */
    @Value("${oss.accessKeySecret}")
    private String accessKeySecret;

    @Autowired
    private SystemFileService systemFileService;

    /**
     * 文件上传
     * @param file
     */
    public String fileUploadFile(MultipartFile file,String originFileName) throws IOException{
        //创建ossClient实例
        OSSClient ossClient=new OSSClient(endpoint,accessKeyId,accessKeySecret);
        //上传文件流
        //判断bucket是否存在
        boolean exist=ossClient.doesBucketExist(bucket);
        if(!exist){
            //创建bucket
            ossClient.createBucket(bucket);
        }

        //按点号分割必须转义
        String[] strings=originFileName.split("\\.");
        String key= String.valueOf(System.currentTimeMillis())+"."+strings[1];
        logger.info(key);
        ossClient.putObject(bucket,key,file.getInputStream());
        //关闭ossclient
        ossClient.shutdown();

        return key;
    }

    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    public int uploadFile(MultipartFile file, int uid,String originFileName) throws IOException{
        String key=fileUploadFile(file, originFileName);

        SystemFile systemFile=new SystemFile();
        systemFile.setFileOriginName(originFileName);
        systemFile.setFileNewName(key);
        systemFile.setFileMime(file.getContentType());
        systemFile.setFileSize(file.getSize());
        systemFile.setCreateTime(new Date());
        systemFile.setCreateId(uid);
        return systemFileService.save(systemFile);
    }

    @Cacheable(value = "imgurl",key = "'access_file'+#p0")
    public String accessFile(String key){
        //创建ossClient实例
        OSSClient ossClient=new OSSClient(endpoint,accessKeyId,accessKeySecret);

        //生成签名URL(替代new Date().getTime() 更加精确)
        Date expiration=new Date(System.currentTimeMillis()+3600*1000);
        URL url=ossClient.generatePresignedUrl(bucket,key,expiration);

        ossClient.shutdown();
        return url.toString();
    }
}
