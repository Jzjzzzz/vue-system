package com.jzj.base.job.mt;

import com.jzj.base.web.service.MtItemService;
import com.jzj.base.web.service.MtShopService;
import com.jzj.job.base.BaseJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * <p>
 * i茅台每日门店和商品列表刷新
 * </p>
 *
 * @author Jzj
 * @since 2024/9/10 11:57
 */
@Slf4j
public class MtDataUpdateJob implements BaseJob {

    @Autowired
    private MtShopService mtShopService;

    @Autowired
    private MtItemService mtItemService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {
            log.info("「刷新数据」开始刷新版本号，预约item，门店shop列表");
            mtItemService.insertMtItem();
            mtShopService.insertMtShop();
        } catch (Exception e){
            log.error("「刷新数据执行报错」:"+ e.getMessage());
        }
    }
}
