package cn.admin.file.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 陈炳坤
 * @date 2018/01/10
 */
public class ValidationUtil {
    /**
     * 校验手机号格式
     * @param mobile
     * @return
     */
    public static boolean checkIsMobile(String mobile){
        String regx = "^1[34578]\\d{9}$";
        Pattern pattern=Pattern.compile(regx);
        Matcher m=pattern.matcher(mobile);
        return m.matches();
    }
}
