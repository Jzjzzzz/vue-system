package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.vo.ProcessQuery;
import com.jzj.base.web.pojo.vo.ProcessTypeVo;
import com.jzj.base.web.pojo.vo.ProcessVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 审批类型Mapper接口
 * 
 * @author Jzj
 * @date 2024-08-01
 */
public interface ProcessMapper extends BaseMapper<Process>
{

    /**
     * 查询审批类型列表
     * 
     * @param process 审批类型
     * @return 审批类型集合
     */
     List<Process> selectProcessList(Process process);

    /**
     * 查询审批类型和审批模板
     */
    List<ProcessTypeVo> findProcessType();

    /**
     * 查询当前用户发起的审核列表
     */
    IPage<ProcessVo> findInit(Page<ProcessVo> page,@Param("vo") ProcessQuery pageParam);
}
