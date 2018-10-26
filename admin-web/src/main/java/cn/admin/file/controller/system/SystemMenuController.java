package com.hzhetun.example.controller.system;

import com.hzhetun.example.pojo.SystemIcon;
import com.hzhetun.example.pojo.SystemMenu;
import com.hzhetun.example.service.SystemIconService;
import com.hzhetun.example.service.SystemMenuService;
import com.hzhetun.example.utils.RestfulResult;
import com.hzhetun.example.utils.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Controller
@RequestMapping(value = "/systemmenu")
public class SystemMenuController {
    @Autowired
    private SystemMenuService systemMenuService;

    @Autowired
    private SystemIconService systemIconService;

    @RequestMapping(value = "query")
    public String query(ModelMap map){
        List<SystemIcon> icons=systemIconService.selectList();
        map.put("icons",icons);
        return "/system/system_menu_list";
    }

    @RequestMapping(value = "findSelectList")
    @ResponseBody
    public RestfulResult findSelectList(){
        //获得上级菜单的下拉列表
        List<SystemMenu> menus=systemMenuService.findMenuSelectList();
        return new RestfulResult(200,"menus",menus);
    }

    @RequestMapping(value = "data")
    @ResponseBody
    public Map<String,Object> data(){
        List<Map<String,Object>> menus=systemMenuService.findAll();
        Map<String,Object> map=new HashMap<String,Object>(4);
        map.put("code",0);
        map.put("msg","");
        map.put("data",menus);
        return map;
    }

    @RequestMapping(value = "/changeMenu")
    @ResponseBody
    public RestfulResult changeMenu(@RequestParam String module){
        Map<String,Object> map=systemMenuService.loadMenu();
        String menus=(String)map.get(module);
        return new RestfulResult(200,"menus",menus);
    }

    @RequestMapping(value = "/addOrUpdate")
    @ResponseBody
    public RestfulResult addOrUpdate(SystemMenu systemMenu){
        if(systemMenu.getPid()==null){
            return RestfulResult.warning(-10,"请选择上级菜单");
        }

        if(StringUtil.isBlank(systemMenu.getName())){
            return RestfulResult.warning(-20,"请填写菜单名称");
        }

        if(StringUtil.isBlank(systemMenu.getUrl())){
            systemMenu.setUrl("javascript:;");
        }

        String msg="保存成功";
        if(systemMenu.getId()!=null){
            msg="修改成功";
        }

        try {
            if(systemMenu.getPid()!=0){
                SystemMenu parent=systemMenuService.findOne(systemMenu.getPid());
                systemMenu.setModule(parent.getModule());
            }
            systemMenuService.saveOrUpdate(systemMenu);
        }catch (Exception e){
            e.printStackTrace();
            return RestfulResult.error500(e.getMessage());
        }

        return RestfulResult.success(msg);
    }

    @RequestMapping(value = "findOne")
    @ResponseBody
    public RestfulResult findOne(@RequestParam int id){
        Map<String,Object> systemMenu=systemMenuService.findParent(id);
        if(systemMenu==null){
            return RestfulResult.warning(-10,"无对应菜单信息");
        }

        return new RestfulResult(200,"systemMenu",systemMenu);
    }

    @ResponseBody
    @RequestMapping(value = "del")
    public RestfulResult del(@RequestParam int id){
        SystemMenu systemMenu=systemMenuService.findOne(id);
        if(systemMenu==null){
            return RestfulResult.warning(-10,"此菜单不存在");
        }

        List<SystemMenu> systemMenus=new ArrayList<SystemMenu>();
        List<SystemMenu> systemMenuList=systemMenuService.getChildrens(systemMenu,systemMenus);

        try{
            systemMenuService.del(systemMenuList);
        }catch (Exception e){
            e.printStackTrace();
            return RestfulResult.error500(e.getMessage());
        }

        return RestfulResult.success("删除成功");
    }
}
