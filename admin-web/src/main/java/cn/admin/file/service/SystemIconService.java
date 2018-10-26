package cn.admin.file.service;


import cn.admin.file.pojo.SystemIcon;

import java.util.List;

/**
 * @author cbk
 * @date 2017/12/23
 */
public interface SystemIconService {
    /**
     *  图标下拉列表查询方法
     * @return List<SystemIcon>
     */
    List<SystemIcon> selectList();
}
