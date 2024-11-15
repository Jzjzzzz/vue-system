package com.jzj.base.handler;

import com.jzj.common.utils.result.BusinessException;
import com.jzj.common.utils.result.ExceptionUtil;
import com.jzj.common.utils.result.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * <p>
 * 异常处理拦截器
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 全局异常处理
     *
     */
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().message("执行了全局异常处理");
    }


    /**
     * 自定义异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R error(BusinessException e) {
        log.error(ExceptionUtil.getMessage(e));
        e.printStackTrace();
        return R.error().code(e.getCode()).message(e.getMessage());
    }

    /**
     * spring security异常
     */
    @ExceptionHandler(AccessDeniedException.class)
    @ResponseBody
    public R error(AccessDeniedException e) throws AccessDeniedException{
        return R.error(e.getMessage());
    }
}
