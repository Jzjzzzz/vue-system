package com.jzj.base.web.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.pojo.vo.*;

import java.util.List;
import java.util.Map;

/**
 * 审批类型Service接口
 * 
 * @author Jzj
 * @date 2024-08-01
 */
public interface ProcessService extends IService<Process>
{
    /**
     * 查询审批类型
     * 
     * @param id 审批类型主键
     * @return 审批类型
     */
    Process selectProcessById(String id);

    /**
     * 查询审批类型列表
     * 
     * @param process 审批类型
     * @return 审批类型集合
     */
    List<Process> selectProcessList(Process process);

    /**
     * 修改审批类型
     * 
     * @param process 审批类型
     * @return 结果
     */
    int updateProcess(Process process);

    /**
     * 批量删除审批类型
     * 
     * @param ids 需要删除的审批类型主键集合
     * @return 结果
     */
    int deleteProcessByIds(String[] ids);

    /**
     * 部署流程定义
     */
    void deployByZip(ProcessTemplate processTemplate);

    /**
     * 查询所有分类和对应模板
     */
    Map<String, List<ProcessTypeVo>> findProcessType();

    /**
     * 开启流程控制
     * @param form 提交表单
     */
    void startUp(ProcessFormVo form);

    /**
     * 审批详情
     * @param id
     * @return
     */
    Map<String, Object> show(String id);

    /**
     * 审核列表
     */
    IPage<ProcessVo> find(ProcessQuery pageParam);

    /**
     * 审核
     */
    void approve(ApprovalVo approvalVo);
}