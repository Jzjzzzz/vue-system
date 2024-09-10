package com.jzj.base.job.mt;

import com.jzj.base.web.service.MtUserService;
import com.jzj.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * i茅台用户预购时间每日重置(0-59分)
 * </p>
 *
 * @author Jzj
 * @since 2024/9/10 10:51
 */
@Slf4j
public class MtPreOrderTimeJob  implements BaseJob {

    @Autowired
    private MtUserService mtUserService;

    @Override
    public void execute(JobExecutionContext context) {
        try {
            log.info("开始重置用户随机预购分钟数");
            mtUserService.resetTimeDaily();
        } catch (Exception e){
            log.error("重置用户随机预购分钟数失败");
        }
    }
}
