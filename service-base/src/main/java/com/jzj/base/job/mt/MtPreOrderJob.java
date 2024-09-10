package com.jzj.base.job.mt;

import com.jzj.base.web.service.MtApiService;
import com.jzj.job.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * i茅台每日预购
 * </p>
 *
 * @author Jzj
 * @since 2024/9/9 17:57
 */
public class MtPreOrderJob implements BaseJob {

    @Autowired
    private MtApiService apiService;

    @Override
    public void execute(JobExecutionContext context) {
        apiService.reservationBatch();
    }
}
