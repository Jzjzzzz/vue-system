package com.jzj.base.utils.sign;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @Author Jzj
 * @Date 2024/8/2 下午2:38
 * @Version 1.0
 * @Message: security工具类
 */
public class SecurityUtils {


    /**
     *  获取登录用户身份校验类
     * @return
     */
    public static Authentication getAuthentication(){
        return SecurityContextHolder.getContext().getAuthentication();
    }

    /**
     * 获取当前登录用户用户名
     */
    public static String getUserName(){
        Authentication auth = getAuthentication();
        if(auth!=null){
            return auth.getName();
        }
        return "";
    }
}
