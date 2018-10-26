package com.hzhetun.example.controller.system;

import com.hzhetun.example.enums.UserStatusEnum;
import com.hzhetun.example.pojo.SystemRole;
import com.hzhetun.example.pojo.SystemUser;
import com.hzhetun.example.service.SystemRoleService;
import com.hzhetun.example.service.SystemUserRoleService;
import com.hzhetun.example.service.SystemUserService;
import com.hzhetun.example.utils.RestfulResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Controller
@RequestMapping(value = "/user")
public class SystemUserController {

    @Autowired
    private SystemUserService systemUserService;

    @Autowired
    private SystemRoleService systemRoleService;

    @Autowired
    private SystemUserRoleService systemUserRoleService;

    private final Logger logger= Logger.getLogger(SystemUserController.class);
    /**
     *查询方法
     */
    @RequestMapping(value = "/query")
    public String query(ModelMap map){
        List<SystemRole> systemRoles=systemRoleService.findRoleSelectList();
        map.put("systemRoles",systemRoles);
        return "/system/user/system_user_list";
    }

    @RequestMapping(value = "/data")
    @ResponseBody
    public Map<String, Object> data(SystemUser systemUser, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit){
        List<SystemUser> users= systemUserService.findUserList(systemUser,page,limit);
        Long count= systemUserService.count(systemUser);
        Map<String,Object> map=new HashMap<String,Object>(4);
        map.put("code",0);
        map.put("msg","");
        map.put("count",count);
        map.put("data",users);
        return map;
    }

    @RequestMapping(value = "/addOrUpdate",method = RequestMethod.POST)
    @ResponseBody
    public RestfulResult addOrUpdate(SystemUser systemUser, @RequestParam(name = "roles[]") String[] roles){
        SystemUser user= systemUserService.findByCondition(systemUser);
        if(systemUser.getId()==null){
            if(user!=null){
                return RestfulResult.warning(-10,"用户名已存在");
            }
        }
        else{
            if(user!=null && user.getId().compareTo(systemUser.getId())!=0){
                return RestfulResult.warning(-10,"用户名已存在");
            }
        }

        if(roles.length==0){
            return RestfulResult.warning(-20,"请至少选择一个角色");
        }

        String msg="";
        if(systemUser.getId()==null){
            msg="保存成功";
        }
        else{
            msg="修改成功";
        }
        try {
            systemUserService.saveOrUpdate(systemUser,roles);
        }catch (Exception e){
            e.printStackTrace();
            return RestfulResult.error500(e.getMessage());
        }
        return RestfulResult.success(msg);
    }

    @RequestMapping(value = "/findOne")
    @ResponseBody
    public RestfulResult findOne(@RequestParam(value = "id") int id){
        SystemUser systemUser= systemUserService.findOne(id);
        if(systemUser==null){
            return RestfulResult.warning(-10,"无对应的用户信息");
        }

        Map<String,Object> map=new HashMap<String,Object>(2);
        map.put("user",systemUser);

        List<Map<String,Object>> systemUserRoles=systemUserRoleService.findByUser(id);
        map.put("userRoles",systemUserRoles);
        return new RestfulResult(200,map);
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public RestfulResult del(@RequestParam(value = "ids") String ids){
        String[] id=ids.split(",");
        for(String s:id){
            int result= systemUserService.del(Integer.parseInt(s));
        }
        return RestfulResult.success("删除成功");
    }

    @RequestMapping(value = "/changeAvatar")
    @ResponseBody
    public RestfulResult changeAvatar(Integer fileId,HttpSession session){
        if(fileId==null){
            return RestfulResult.warning(-10,"请先上传图片");
        }

        SystemUser user=(SystemUser) session.getAttribute("user");
        Integer uid=user.getId();
        SystemUser systemUser=systemUserService.findOne(uid);
        systemUser.setImg(fileId);
        systemUserService.saveOrUpdate(systemUser);

        return RestfulResult.success("修改成功");
    }

    @RequestMapping(value = "changeStatus",method = RequestMethod.POST)
    @ResponseBody
    public RestfulResult changeStatus(@RequestParam int id, @RequestParam Boolean checked){
        SystemUser systemUser=systemUserService.findOne(id);
        if(systemUser==null){
            return RestfulResult.warning(-10,"用户不存在");
        }

        String msg="";
        if(checked){
            systemUser.setStatus(UserStatusEnum.ENABLE.getStatus());
            msg="开启成功";
        }
        else{
            systemUser.setStatus(UserStatusEnum.FORBIDDEN.getStatus());
            msg="禁用成功";
        }

        try {
            systemUserService.saveOrUpdate(systemUser);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return RestfulResult.error500("网络错误");
        }

        return RestfulResult.success(msg);
    }
}
