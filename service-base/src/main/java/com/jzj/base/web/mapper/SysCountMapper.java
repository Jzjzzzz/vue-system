package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.SysCount;

import java.util.List;

/**
 * <p>
 * 统计归档 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-08-23
 */
public interface SysCountMapper extends BaseMapper<SysCount> {

    /**
     * 获取昨天的统计数据
     * @return
     */
    SysCount getBeforeDayCount();

    /**
     * 获取前7天的统计数据
     * @return
     */
    List<SysCount> getSevenCount();

    /**
     * 判断当前日期是否已生成数据
     */
    SysCount getNowDateCount();
}
