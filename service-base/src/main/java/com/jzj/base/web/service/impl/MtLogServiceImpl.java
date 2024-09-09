package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.aspectj.manager.AsyncFactory;
import com.jzj.base.aspectj.manager.AsyncManager;
import com.jzj.base.web.mapper.MtLogMapper;
import com.jzj.base.web.pojo.entity.MtLog;
import com.jzj.base.web.pojo.entity.MtUser;
import com.jzj.base.web.service.MtLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

/**
 * <p>
 * i茅台执行日志Service业务层处理
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Service
public class MtLogServiceImpl extends ServiceImpl<MtLogMapper, MtLog> implements MtLogService {

    @Autowired
    private MtLogMapper mtLogMapper;

    /**
     * 查询i茅台执行日志
     *
     * @param id i茅台执行日志主键
     * @return i茅台执行日志
     */
    @Override
    public MtLog selectMtLogById(String id) {
        return mtLogMapper.selectById(id);
    }

    /**
     * 查询i茅台执行日志列表
     *
     * @param mtLog i茅台执行日志
     * @return i茅台执行日志
     */
    @Override
    public List<MtLog> selectMtLogList(MtLog mtLog) {
        return mtLogMapper.selectMtLogList(mtLog);
    }

    /**
     * 批量删除i茅台执行日志
     *
     * @param ids 需要删除的i茅台执行日志主键
     * @return 结果
     */
    @Override
    public int deleteMtLogByIds(String[] ids) {
        return mtLogMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 日志记录
     * @param iUser 用户
     * @param logContent 内容
     */
    @Override
    public void reservation(MtUser iUser, String logContent) {
        MtLog log = new MtLog();
        if (logContent.contains("报错")) {
            log.setStatus("1");
        } else {
            log.setStatus("0");
        }
        log.setMobile(iUser.getMobile());
        log.setLogContent(logContent);

        AsyncManager.me().execute(AsyncFactory.mtLogRecords(log));
        //TODO 预约推送功能
    }
}
