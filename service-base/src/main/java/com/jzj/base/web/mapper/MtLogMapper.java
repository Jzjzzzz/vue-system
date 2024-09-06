package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.MtLog;

import java.util.List;

/**
 * <p>
 * i茅台执行日志Mapper接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtLogMapper extends BaseMapper<MtLog> {
    /**
     * 查询i茅台执行日志列表
     *
     * @param mtLog i茅台执行日志
     * @return i茅台执行日志集合
     */
    List<MtLog> selectMtLogList(MtLog mtLog);

}
