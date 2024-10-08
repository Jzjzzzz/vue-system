package com.jzj.common.utils;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * <p>
 * security工具类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class SecurityUtils {

    /**
     *  获取登录用户身份校验类
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
