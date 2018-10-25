package com.hzhetun.example.service.serviceimpl;

import com.hzhetun.example.mapper.SystemMenuMapper;
import com.hzhetun.example.pojo.SystemMenu;
import com.hzhetun.example.service.SystemMenuService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */
@Service
@CacheConfig(cacheNames = "menus")
public class SystemMenuSerivceImpl implements SystemMenuService {

    @Autowired
    private SystemMenuMapper systemMenuMapper;

    private Logger logger= Logger.getLogger(SystemMenuSerivceImpl.class);

    @Override
    public List<SystemMenu> findByCondition(SystemMenu systemMenu) {
        return systemMenuMapper.findByCondition(systemMenu);
    }

    @Override
    @Cacheable(key = "'findByLevel'+#p0")
    public List<SystemMenu> findByLevel(int level){
        logger.info("没有走缓存");
        return systemMenuMapper.findByLevel(level);
    }

    @Override
    public List<Map<String,Object>> findByPid(int pid){
        return systemMenuMapper.findByPid(pid);
    }

    @Override
    public List<SystemMenu> findByParentId(int pid) {
        return systemMenuMapper.findByParentId(pid);
    }

    @Override
    @Cacheable(key = "'findAll'")
    public List<Map<String,Object>> findAll(){
        List<Map<String,Object>> menus=new ArrayList<Map<String,Object>>();
        List<Map<String,Object>> firstLevelMenus=findByPid(0);
        if(firstLevelMenus.size()>0){
            for(Map<String,Object> firstLevel:firstLevelMenus){
                menus.add(firstLevel);
                List<Map<String,Object>> secondLevelMenus=findByPid((Integer) firstLevel.get("id"));
                if(secondLevelMenus.size()>0){
                    for(Map<String,Object> secondLevel:secondLevelMenus){
                        menus.add(secondLevel);
                        List<Map<String,Object>> thirdLevelMenus=findByPid((Integer) secondLevel.get("id"));
                        if(thirdLevelMenus.size()>0){
                            for(Map<String,Object> thirdLevel:thirdLevelMenus){
                                menus.add(thirdLevel);
                            }
                        }
                    }
                }
            }
        }

        return menus;
    }

    @Override
    @Cacheable(key = "'findMenuSelectList'")
    public List<SystemMenu> findMenuSelectList(){
        List<SystemMenu> menus=new ArrayList<SystemMenu>();
        List<SystemMenu> firstLevelMenus=findByParentId(0);
        if(firstLevelMenus.size()>0){
            for(SystemMenu firstLevel:firstLevelMenus){
                List<SystemMenu> secondLevelMenus=findByParentId(firstLevel.getId());
                menus.add(firstLevel);
                if(secondLevelMenus.size()>0){
                    for(SystemMenu secondLevel:secondLevelMenus){
                        menus.add(secondLevel);
                    }
                }
            }
        }

        return menus;
    }

    @Override
    @Cacheable(key = "'loadMenu'")
    public Map<String, Object> loadMenu() {
        logger.info("菜单模块没有走缓存");

        //获得所有的模块
        Map<String,Object> map=new HashMap<String,Object>(16);
        List<SystemMenu> modules=findByLevel(1);
        for(SystemMenu module:modules){
            List<Map<String,Object>> firstLevelMenus=findByPid(module.getId());
            StringBuffer html=new StringBuffer();
            if(firstLevelMenus.size()>0){
                for(int i=0;i<firstLevelMenus.size();i++){
                    Map<String,Object> firstLevelMenu=firstLevelMenus.get(i);
                    //默认第一个展开
                    if(i==0){
                        html.append("<li class='layui-nav-item layui-nav-itemed'>");
                    }
                    else{
                        html.append("<li class='layui-nav-item'>");
                    }
                    html.append("<a href='javascript:;'><i class='layui-icon'>"+(firstLevelMenu.get("unicode")==null?"":firstLevelMenu.get("unicode"))+"</i>&nbsp;"+firstLevelMenu.get("name")+"</a>");
                    List<Map<String, Object>> secondLevelMenus=systemMenuMapper.findByPid((Integer) firstLevelMenu.get("id"));
                    if(secondLevelMenus.size()>0){
                        for(Map<String, Object> secondLevelMenu:secondLevelMenus){
                            html.append("<dl class='layui-nav-child'>");
                            html.append("<dd>");
                            html.append("<a href='"+secondLevelMenu.get("url")+"'>");
                            html.append("&nbsp;&nbsp;&nbsp;&nbsp;<i class='layui-icon'>"+(secondLevelMenu.get("unicode")==null?"":secondLevelMenu.get("unicode"))+"</i>&nbsp;");
                            html.append(secondLevelMenu.get("name")+"</a>");
                            html.append("</dd>");
                            html.append("</dl>");
                        }
                    }
                    html.append("</li>");
                }
            }

            map.put(module.getModule(),html.toString());
        }

        return map;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void saveOrUpdate(SystemMenu systemMenu) {
        //一级
        if(systemMenu.getPid()==0){
            systemMenu.setLevel(1);
        }
        else{
            SystemMenu sm=findOne(systemMenu.getPid());
            systemMenu.setLevel(sm.getLevel()+1);
        }
        //新增
        if(systemMenu.getId()==null){
            systemMenuMapper.insertSelective(systemMenu);
        }
        else{
            systemMenuMapper.updateByPrimaryKeySelective(systemMenu);
        }
    }

    @Override
    public SystemMenu findOne(int id) {
        SystemMenu systemMenu=systemMenuMapper.selectByPrimaryKey(id);
        return systemMenu;
    }

    @Override
    public Map<String, Object> findParent(int id) {
        return systemMenuMapper.findParent(id);
    }

    @Override
    public List<SystemMenu> getChildrens(SystemMenu systemMenu, List<SystemMenu> systemMenus) {
        systemMenus.add(systemMenu);
        List<SystemMenu> systemMenuList=findByParentId(systemMenu.getId());
        if(systemMenuList.size()>0){
            for(SystemMenu sm:systemMenuList){
                systemMenus.add(sm);
                getChildrens(sm,systemMenus);
            }
        }

        return systemMenus;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @CacheEvict(allEntries = true)
    public void del(List<SystemMenu> systemMenus) {
        for(SystemMenu systemMenu:systemMenus){
            del(systemMenu);
        }
    }

    @Override
    public void del(SystemMenu systemMenu) {
        systemMenuMapper.deleteByPrimaryKey(systemMenu.getId());
    }

    @Override
    @Cacheable(key = "'findByUrl'+#p0")
    public SystemMenu findByUrl(String url) {
        return systemMenuMapper.findByUrl(url);
    }
}
