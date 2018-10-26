package cn.admin.file.service;

import cn.admin.file.pojo.SystemMenu;

import java.util.List;
import java.util.Map;

/**
 * @author cbk
 * @date 2017/12/23
 */

public interface SystemMenuService {
    /**
     * 根据条件动态查询方法
     * @param systemMenu
     * @return List<SystemMenu>
     */
    List<SystemMenu> findByCondition(SystemMenu systemMenu);

    /**
     * 根据上级pid查询方法
     * @param pid
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findByPid(int pid);

    /**
     * 根据上级pid查询方法
     * @param pid
     * @return List<SystemMenu>
     */
    List<SystemMenu> findByParentId(int pid);

    /**
     * 根据主键查询方法
     * @param id
     * @return SystemMenu
     */
    SystemMenu findOne(int id);

    /**
     * 查询上级菜单的方法
     * @param id
     * @return Map<String,Object>
     */
    Map<String,Object> findParent(int id);

    /**
     * 菜单树形列表的方法
     * @return List<Map<String,Object>>
     */
    List<Map<String,Object>> findAll();

    /**
     * 新增菜单的下拉列表的方法
     * @return List<SystemMenu>
     */

    List<SystemMenu> findMenuSelectList();

    /**
     * 根据级别查询的方法
     * @param level
     * @return
     */
    List<SystemMenu> findByLevel(int level);

    /**
     * 加载菜单的方法
     * @return Map<String,Object>
     */
    Map<String,Object> loadMenu();

    /**
     * 保存或者修改的方法
     * @param systemMenu
     */
    void saveOrUpdate(SystemMenu systemMenu);

    /**
     * 获得某个菜单子菜单的方法
     * @param systemMenu
     * @param systemMenus
     * @return List<SystemMenu>
     */
    List<SystemMenu> getChildrens(SystemMenu systemMenu, List<SystemMenu> systemMenus);

    /**
     * 删除方法
     * @param systemMenus
     */
    void del(List<SystemMenu> systemMenus);

    /**
     * 删除方法
     * @param systemMenu
     */
    void del(SystemMenu systemMenu);

    /**
     * 根据菜单url查询的方法
     * @param url
     * @return
     */
    SystemMenu findByUrl(String url);
}
