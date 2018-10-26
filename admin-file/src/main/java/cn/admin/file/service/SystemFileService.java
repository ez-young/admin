package com.hzhetun.example.service;

import com.hzhetun.example.pojo.SystemFile;

/**
 * @author cbk
 * @date 2017/12/23
 */

public interface SystemFileService {
    /**
     * 上传文件信息保存的方法
     * @param systemFile
     * @return int
     */
    int save(SystemFile systemFile);

    /**
     * 根据主键查询的方法
     * @param id
     * @return SystemFile
     */
    SystemFile findOne(Integer id);
}
