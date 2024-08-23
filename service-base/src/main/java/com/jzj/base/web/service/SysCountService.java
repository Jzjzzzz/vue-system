package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.SysCount;
import com.jzj.base.web.pojo.vo.BeforeDayCountVo;

/**
 * <p>
 * 统计归档 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
public interface SysCountService extends IService<SysCount> {

    /**
     * 获取首页统计数据
     *
     * @return 实体
     */
    SysCount getCount();

    /**
     * 获取过去七日统计数据
     *
     * @return 实体
     */
    BeforeDayCountVo getSevenCount();
}
