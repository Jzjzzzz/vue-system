package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.MtLog;

import java.util.List;

/**
 * <p>
 * i茅台执行日志Service接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtLogService extends IService<MtLog> {
    /**
     * 查询i茅台执行日志
     *
     * @param id i茅台执行日志主键
     * @return i茅台执行日志
     */
    MtLog selectMtLogById(String id);

    /**
     * 查询i茅台执行日志列表
     *
     * @param mtLog i茅台执行日志
     * @return i茅台执行日志集合
     */
    List<MtLog> selectMtLogList(MtLog mtLog);

    /**
     * 新增i茅台执行日志
     *
     * @param mtLog i茅台执行日志
     * @return 结果
     */
    int insertMtLog(MtLog mtLog);

    /**
     * 修改i茅台执行日志
     *
     * @param mtLog i茅台执行日志
     * @return 结果
     */
    int updateMtLog(MtLog mtLog);

    /**
     * 批量删除i茅台执行日志
     *
     * @param ids 需要删除的i茅台执行日志主键集合
     * @return 结果
     */
    int deleteMtLogByIds(String[] ids);

    /**
     * 删除i茅台执行日志信息
     *
     * @param id i茅台执行日志主键
     * @return 结果
     */
    int deleteMtLogById(String id);
}
