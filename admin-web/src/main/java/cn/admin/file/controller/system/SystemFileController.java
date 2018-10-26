package cn.admin.file.controller.system;

import cn.admin.file.pojo.SystemUser;
import cn.admin.file.utils.RestfulResult;
import cn.admin.file.client.resttemplate.FileClient;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;


/**
 * @author cbk
 * @date 2017/12/23
 */
@RestController
@RequestMapping(value = "/file")
public class SystemFileController{

    private Logger logger= Logger.getLogger(SystemFileController.class);

    @Autowired
    private FileClient fileClient;

    @RequestMapping(value = "/upload")
    public RestfulResult upload(MultipartFile file, HttpSession session){
        logger.info("文件名称:"+file.getOriginalFilename()+"文件类型:"+file.getContentType()+"文件大小:"+file.getSize());
        SystemUser systemUser=(SystemUser) session.getAttribute("user");
        return fileClient.fileUploadService(file,systemUser.getId());
    }

    @GetMapping(value = "/getImgUrl")
    public RestfulResult getImgUrl(@RequestParam Integer imgId){
        return fileClient.getImgUrl(imgId);
    }
}
