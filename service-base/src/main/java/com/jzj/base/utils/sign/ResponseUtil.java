package com.jzj.base.utils.sign;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jzj.common.utils.result.R;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * <p>
 * 响应体工具类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class ResponseUtil {
    public static void out(HttpServletResponse response, R r) {
        ObjectMapper mapper = new ObjectMapper();
        response.setStatus(HttpStatus.OK.value());
        response.setContentType(MediaType.APPLICATION_JSON_UTF8_VALUE);
        try {
            mapper.writeValue(response.getWriter(), r);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
