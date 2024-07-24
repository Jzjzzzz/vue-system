package com.jzj.base.web.pojo.vo;


import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 定时任务详情
 * </p>
 *
 * @author Jzj
 * @date Created in 2022-08-08
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@ApiModel("定时任务详情")
public class JobVo implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 定时任务全类名
     */
    private String jobClassName;
    /**
     * 任务组名
     */
    private String jobGroupName;
    /**
     * 定时任务cron表达式
     */
    private String cronExpression;
}
