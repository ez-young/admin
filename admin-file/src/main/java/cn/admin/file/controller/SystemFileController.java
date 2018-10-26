package cn.admin.file.controller;

import cn.admin.file.service.SystemFileService;
import cn.admin.file.utils.RestfulResult;
import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSSException;
import cn.admin.file.api.FileUploadApi;
import cn.admin.file.pojo.SystemFile;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;


/**
 * @author cbk
 * @date 2017/12/23
 */
@RestController
@RequestMapping(value = "/file")
public class SystemFileController {

    private Logger logger= Logger.getLogger(SystemFileController.class);

    @Autowired
    private FileUploadApi fileUploadApi;

    @Autowired
    private SystemFileService systemFileService;

    @Value("${server.port}")
    private Integer port;

    @RequestMapping(value = "/upload")
    public RestfulResult upload(MultipartFile file, @RequestParam Integer uid, @RequestParam String originFileName){
        logger.info("调用的服务端口号:"+port);
        if(file==null || file.isEmpty()){
            return RestfulResult.warning(-10,"文件不能为空");
        }

        logger.info("文件:"+file.getName()+","+originFileName+","+file.getContentType());

        //上传文件到阿里云
        int code=200;
        String message="";
        int id=0;
        try {
            id=fileUploadApi.uploadFile(file,uid,originFileName);
        } catch (OSSException oe){//服务器异常
            logger.info("Caught an OSSException, which means your request made it to OSS,but was rejected with an error response for some reason.");
            logger.info("Error Message: " + oe.getErrorMessage());
            logger.info("   Error Code: " + oe.getErrorCode());
            logger.info("   Request ID: " + oe.getRequestId());
            logger.info("      Host ID: " + oe.getHostId());
            code=-10;
            message="服务器端错误";
        } catch (IOException ie){//
            code=-20;
            message="文件上传时发生IO异常";
        } catch (ClientException ce){//网络异常
            code=-30;
            message="客户端异常";
        }
        return new RestfulResult(code,message,"id",id);
    }

    @GetMapping(value = "/getImgUrl")
    public RestfulResult getImgUrl(@RequestParam Integer imgId){
        logger.info("调用的服务端口号:"+port);
        if(imgId==null){
            return RestfulResult.warning(-10,"imgId不能为null");
        }

        SystemFile systemFile=systemFileService.findOne(imgId);
        if(systemFile==null){
            return RestfulResult.warning(-20,"无对应的头像信息");
        }

        String url=fileUploadApi.accessFile(systemFile.getFileNewName());
        return new RestfulResult(200,"url",url);
    }
}
