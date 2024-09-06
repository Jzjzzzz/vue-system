package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.mapper.MtLogMapper;
import com.jzj.base.web.pojo.entity.MtLog;
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
     * 新增i茅台执行日志
     *
     * @param mtLog i茅台执行日志
     * @return 结果
     */
    @Override
    public int insertMtLog(MtLog mtLog) {
        return mtLogMapper.insert(mtLog);
    }

    /**
     * 修改i茅台执行日志
     *
     * @param mtLog i茅台执行日志
     * @return 结果
     */
    @Override
    public int updateMtLog(MtLog mtLog) {
        return mtLogMapper.updateById(mtLog);
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
     * 删除i茅台执行日志信息
     *
     * @param id i茅台执行日志主键
     * @return 结果
     */
    @Override
    public int deleteMtLogById(String id) {
        return mtLogMapper.deleteById(id);
    }
}
