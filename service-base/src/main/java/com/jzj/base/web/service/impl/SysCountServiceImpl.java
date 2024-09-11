package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.service.*;
import com.jzj.common.utils.DateUtils;
import com.jzj.base.web.mapper.SysCountMapper;
import com.jzj.base.web.pojo.entity.SysCount;
import com.jzj.base.web.pojo.vo.BeforeDayCountVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 统计归档 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
@Service
public class SysCountServiceImpl extends ServiceImpl<SysCountMapper, SysCount> implements SysCountService {

    @Autowired
    private SysCountMapper sysCountMapper;

    @Autowired
    private MtUserService mtUserService;

    @Autowired
    private MtItemService mtItemService;

    @Autowired
    private MtShopService mtShopService;

    @Autowired
    private MtLogService mtLogService;

    @Override
    public SysCount getCount() {
        SysCount sysCount = new SysCount();
        sysCount.setArticleCount(mtUserService.count());
        sysCount.setLikeCount(mtLogService.count());
        sysCount.setClickCount(mtShopService.count());
        sysCount.setSummaryCount(mtItemService.count());
        return sysCount;
    }

    @Override
    public BeforeDayCountVo getSevenCount() {
        BeforeDayCountVo beforeDayCountVo = new BeforeDayCountVo();
        List<Long> clickDayCounts = new ArrayList<>();
        List<Long> likeDayCounts = new ArrayList<>();
        List<String> dateList = new ArrayList<>();
        List<SysCount> list = sysCountMapper.getSevenCount();
        for (SysCount sysCount : list) {
            clickDayCounts.add(sysCount.getClickDayCount());
            likeDayCounts.add(sysCount.getLikeDayCount());
            dateList.add(DateUtils.dateToStrLong(sysCount.getCreateTime()));
        }
        beforeDayCountVo.setLikeDayCounts(likeDayCounts);
        beforeDayCountVo.setClickDayCounts(clickDayCounts);
        beforeDayCountVo.setDateList(dateList);
        return beforeDayCountVo;
    }
}
