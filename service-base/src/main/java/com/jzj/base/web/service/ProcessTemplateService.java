package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.pojo.vo.ProcessFormVo;

import java.util.List;
/**
 * 审批模板Service接口
 * 
 * @author Jzj
 * @date 2024-07-30
 */
public interface ProcessTemplateService extends IService<ProcessTemplate>
{
    /**
     * 查询审批模板
     * 
     * @param id 审批模板主键
     * @return 审批模板
     */
    ProcessTemplate selectProcessTemplateById(String id);

    /**
     * 查询审批模板列表
     * 
     * @param processTemplate 审批模板
     * @return 审批模板集合
     */
    List<ProcessTemplate> selectProcessTemplateList(ProcessTemplate processTemplate);

    /**
     * 新增审批模板
     * 
     * @param processTemplate 审批模板
     * @return 结果
     */
    int insertProcessTemplate(ProcessTemplate processTemplate);

    /**
     * 修改审批模板
     * 
     * @param processTemplate 审批模板
     * @return 结果
     */
    int updateProcessTemplate(ProcessTemplate processTemplate);

    /**
     * 批量删除审批模板
     * 
     * @param ids 需要删除的审批模板主键集合
     * @return 结果
     */
    int deleteProcessTemplateByIds(String[] ids);

    /**
     * 删除审批模板信息
     * 
     * @param id 审批模板主键
     * @return 结果
     */
    int deleteProcessTemplateById(String id);

    /**
     * 发布
     * @param id 模板id
     */
    void publish(String id);

    /**
     * 开启流程控制
     * @param form 提交表单
     */
    void startUp(ProcessFormVo form);
}
