package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.MtLog;
import com.jzj.base.web.pojo.entity.MtUser;

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
     * 批量删除i茅台执行日志
     *
     * @param ids 需要删除的i茅台执行日志主键集合
     * @return 结果
     */
    int deleteMtLogByIds(String[] ids);

    /**
     * 日志记录
     * @param iUser 用户
     * @param logContent 内容
     */
    void reservation(MtUser iUser, String logContent);

    /**
     * 新增操作日志
     *
     * @param iLog 操作日志对象
     */
    public int insertLog(MtLog iLog);
}
