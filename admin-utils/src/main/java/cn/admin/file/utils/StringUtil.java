package com.hzhetun.example.utils;

import java.util.UUID;

/**
 * @author cbk
 * @date 2017/12/23
 */
public class StringUtil {
    /**
     * 字符串为null 或者 ""返回true
     * @param str
     * @return
     */
    public static boolean isBlank(String str){
        return str==null || "".equals(str.trim());
    }

    /**
     * 字符串不为null 并且 不等于"" 返回true
     * @param str
     * @return
     */
    public static boolean isNotBlank(String str){
        return str!=null && !"".equals(str.trim());
    }

    /**
     * 获得uuid
     * @return
     */
    public static String getUUID(){
        UUID uuid=UUID.randomUUID();
        String str = uuid.toString();
        String uuidStr=str.replace("-", "");
        return uuidStr;
    }
}
