package com.jzj.base.job.mt;

import com.jzj.base.web.service.MtApiService;
import com.jzj.job.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * i茅台旅行收益获取
 * </p>
 *
 * @author Jzj
 * @since 2024/9/10 11:47
 */
public class MtTravelJob implements BaseJob {

    @Autowired
    private MtApiService apiService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        apiService.getTravelRewardBatch();
    }
}
