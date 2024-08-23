package com.jzj.base.utils.sign;

/**
 * <p>
 * 处理并记录日志文件
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class LogUtils {
    public static String getBlock(Object msg)
    {
        if (msg == null)
        {
            msg = "";
        }
        return "[" + msg.toString() + "]";
    }
}
