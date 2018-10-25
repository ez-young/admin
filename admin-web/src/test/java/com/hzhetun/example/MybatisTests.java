package com.hzhetun.example;

import com.hzhetun.example.mapper.SystemFileMapper;
import com.hzhetun.example.pojo.SystemFile;
import com.hzhetun.example.pojo.SystemRole;
import com.hzhetun.example.service.SystemRoleService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class MybatisTests extends BaseTests{
    @Autowired
    private SystemFileMapper systemFileMapper;

    @Autowired
    private SystemRoleService systemRoleService;

    @Test
    public void test1(){
        SystemFile systemFile=systemFileMapper.selectByPrimaryKey(1);
        logger.info("文件名称:"+systemFile.getFileOriginName());
    }

    @Test
    public void testTransaction(){
        SystemRole systemRole=new SystemRole();
        systemRole.setName("test");
        systemRole.setField("heloo");
//        systemRoleService.save(systemRole);
        /*try {
            systemRoleService.save(systemRole);
        }catch (Exception e){
            e.printStackTrace();
            logger.info("错误信息:"+e.getMessage());
        }*/
    }
}
