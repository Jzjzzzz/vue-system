package com.jzj.job.mapper;


import com.jzj.job.pojo.JobAndTrigger;

import java.util.List;

/**
 * <p>
 * JobMapper
 * </p>
 *
 * @author Jzj
 * @since 2022-08-08
 */
public interface JobMapper {
    /**
     * 查询定时作业和触发器列表
     *
     * @return 定时作业和触发器列表
     */
    List<JobAndTrigger> list(JobAndTrigger jobAndTrigger);
}
