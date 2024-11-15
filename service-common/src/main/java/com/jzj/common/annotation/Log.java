package com.jzj.common.annotation;


import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.enums.OperatorType;

import java.lang.annotation.*;

/**
 * <p>
 * 自定义操作日志记录注解
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 模块
     */
    String title() default "";

    /**
     * 功能
     */
    BusinessType businessType() default BusinessType.OTHER;

    /**
     * 操作人类别
     */
    OperatorType operatorType() default OperatorType.MANAGE;

    /**
     * 是否保存请求的参数
     */
    boolean isSaveRequestData() default true;

    /**
     * 是否保存响应的参数
     */
    boolean isSaveResponseData() default true;
}
