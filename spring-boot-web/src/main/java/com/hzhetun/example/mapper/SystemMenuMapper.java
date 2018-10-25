package com.hzhetun.example.mapper;

import com.hzhetun.example.pojo.SystemMenu;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * menu mapper interface
 * @author cbk
 * @date 2017/12/23
 */

@Repository
public interface SystemMenuMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param id 主键
     * @return int 影响的行数
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param record
     * @return int
     */
    int insert(SystemMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param record
     * @return int
     */
    int insertSelective(SystemMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param id
     * @return SystemMenu
     */
    SystemMenu selectByPrimaryKey(Integer id);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param record
     * @return int
     */
    int updateByPrimaryKeySelective(SystemMenu record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table system_menu
     *
     * @mbg.generated
     * @param record
     * @return int
     */
    int updateByPrimaryKey(SystemMenu record);

    /**
     * 动态查询
     * @param systemMenu
     * @return List<SystemMenu>
     */
    List<SystemMenu> findByCondition(SystemMenu systemMenu);

    /**
     * 根据上级pid查询
     * @param pid
     * @return
     */
    List<Map<String,Object>> findByPid(int pid);

    /**
     * 根据上级pid查询
     * @param pid
     * @return
     */
    List<SystemMenu> findByParentId(int pid);

    /**
     * 查询上级菜单的方法
     * @param id
     * @return
     */
    Map<String,Object> findParent(int id);

    /**
     * 根据级别查询
     * @param level
     * @return
     */
    List<SystemMenu> findByLevel(int level);

    /**
     * 根据菜单url查询
     * @param url
     * @return
     */
    SystemMenu findByUrl(String url);
}