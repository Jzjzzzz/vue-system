package com.jzj.base.job.mt;

import com.jzj.base.web.service.MtApiService;
import com.jzj.job.base.BaseJob;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * i茅台预购结果获取
 * </p>
 *
 * @author Jzj
 * @since 2024/9/10 14:12
 */
public class MtPreOrderResultJob implements BaseJob {

    @Autowired
    private MtApiService apiService;

    @Override
    public void execute(JobExecutionContext context) {
        apiService.appointmentResults();
    }
}
