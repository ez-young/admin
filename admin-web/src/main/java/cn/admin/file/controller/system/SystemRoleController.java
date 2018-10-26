package cn.admin.file.controller.system;

import cn.admin.file.pojo.SystemRole;
import cn.admin.file.utils.RestfulResult;
import cn.admin.file.utils.StringUtil;
import com.baomidou.mybatisplus.plugins.Page;
import cn.admin.file.service.SystemRoleService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Controller
@RequestMapping(value = "/systemrole")
public class SystemRoleController {
    @Autowired
    private SystemRoleService systemRoleService;

    private Logger logger= Logger.getLogger(SystemRoleController.class);

    @RequestMapping(value = "/query")
    public String query(){
        return "/system/system_role_list";
    }

    @RequestMapping(value = "/data")
    @ResponseBody
    public Map<String,Object> data(SystemRole systemRole, @RequestParam(value = "page") int page, @RequestParam(value = "limit") int limit){
        Page<SystemRole> pages=systemRoleService.findRoleList(systemRole,page, limit);

        Map<String,Object> map=new HashMap<String,Object>(4);
        map.put("code",0);
        map.put("msg","");
        map.put("data",pages.getRecords());
        map.put("count",pages.getTotal());

        return map;
    }

    @RequestMapping(value = "/addOrUpdate")
    @ResponseBody
    public RestfulResult addOrUpdate(SystemRole systemRole){
        if(StringUtil.isBlank(systemRole.getName())){
            return RestfulResult.warning(-10,"请填写角色名称");
        }

        if(StringUtil.isBlank(systemRole.getField())){
            return RestfulResult.warning(-20,"请填写角色域");
        }

        String msg="保存成功";
        if(systemRole.getId()!=null){
            msg="修改成功";
        }

        try {
            systemRoleService.saveOrUpdate(systemRole);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return RestfulResult.error500("系统错误");
        }


        return RestfulResult.success(msg);
    }

    @RequestMapping(value = "findOne")
    @ResponseBody
    public RestfulResult findOne(@RequestParam int id){
        SystemRole systemRole=systemRoleService.findOne(id);
        if(systemRole==null){
            return RestfulResult.warning(-10,"角色不存在");
        }

        return new RestfulResult(200,"systemrole",systemRole);
    }

    @RequestMapping(value = "/del")
    @ResponseBody
    public RestfulResult del(@RequestParam(value = "ids") String ids){
        try {
            systemRoleService.del(ids);
        }catch (Exception e){
            e.printStackTrace();
            logger.info(e.getMessage());
            return RestfulResult.error500("系统错误");
        }

        return RestfulResult.success("删除成功");
    }
}
