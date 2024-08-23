package com.jzj.base.utils.sign;

import com.anji.captcha.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * <p>
 * 验证码工具类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class VerifyCodeUtils {

    public static final String getRemoteId(HttpServletRequest request) {
        String xfwd = request.getHeader("X-Forwarded-For");
        String ip = getRemoteIpFromXfwd(xfwd);
        String ua = request.getHeader("user-agent");
        return StringUtils.isNotBlank(ip) ? ip + ua : request.getRemoteAddr() + ua;
    }

    private static String getRemoteIpFromXfwd(String xfwd) {
        if (StringUtils.isNotBlank(xfwd)) {
            String[] ipList = xfwd.split(",");
            return StringUtils.trim(ipList[0]);
        } else {
            return null;
        }
    }
}
