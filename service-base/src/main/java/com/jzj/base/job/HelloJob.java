package com.jzj.base.job;


import com.jzj.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;

import java.util.Date;

/**
 * <p>
 * 定时任务使用案例
 * </p>
 *
 * @author Jzj
 * @since 2022-08-22
 */
@Slf4j
public class HelloJob implements BaseJob {

    @Override
    public void execute(JobExecutionContext context) {
        System.out.println("Hello Job 执行时间:"+new Date());
    }
}