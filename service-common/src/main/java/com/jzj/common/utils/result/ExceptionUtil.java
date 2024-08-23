package com.jzj.common.utils.result;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;


/**
 * <p>
 * 自定义异常工具类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class ExceptionUtil {
    public static String getMessage(Exception e) {
        StringWriter sw = null;
        PrintWriter pw = null;
        try {
            sw = new StringWriter();
            pw = new PrintWriter(sw);
            // 将出错的栈信息输出到printWriter中
            e.printStackTrace(pw);
            pw.flush();
            sw.flush();
        } finally {
            if (sw != null) {
                try {
                    sw.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (pw != null) {
                pw.close();
            }
        }
        return sw.toString();
    }
}
