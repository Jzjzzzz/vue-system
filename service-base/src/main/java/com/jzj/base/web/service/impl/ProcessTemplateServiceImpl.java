package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.mapper.ProcessTemplateMapper;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.service.ProcessService;
import com.jzj.base.web.service.ProcessTemplateService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * 审批模板Service业务层处理
 * 
 * @author Jzj
 * @date 2024-07-30
 */
@Service
public class ProcessTemplateServiceImpl extends ServiceImpl<ProcessTemplateMapper,ProcessTemplate> implements ProcessTemplateService {

    @Autowired
    private ProcessTemplateMapper processTemplateMapper;

    @Autowired
    private ProcessService processService;

    /**
     * 查询审批模板
     * 
     * @param id 审批模板主键
     * @return 审批模板
     */
    @Override
    public ProcessTemplate selectProcessTemplateById(String id) {
        return processTemplateMapper.selectById(id);
    }

    /**
     * 查询审批模板列表
     * 
     * @param processTemplate 审批模板
     * @return 审批模板
     */
    @Override
    public List<ProcessTemplate> selectProcessTemplateList(ProcessTemplate processTemplate) {
        return processTemplateMapper.selectProcessTemplateList(processTemplate);
    }

    /**
     * 新增审批模板
     * 
     * @param processTemplate 审批模板
     * @return 结果
     */
    @Override
    public int insertProcessTemplate(ProcessTemplate processTemplate) {
        return processTemplateMapper.insert(processTemplate);
    }

    /**
     * 修改审批模板
     * 
     * @param processTemplate 审批模板
     * @return 结果
     */
    @Override
    public int updateProcessTemplate(ProcessTemplate processTemplate) {
        return processTemplateMapper.updateById(processTemplate);
    }

    /**
     * 批量删除审批模板
     * 
     * @param ids 需要删除的审批模板主键
     * @return 结果
     */
    @Override
    public int deleteProcessTemplateByIds(String[] ids) {
        return processTemplateMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 发布
     * @param id 模板id
     */
    @Transactional
    @Override
    public void publish(String id) {
        ProcessTemplate processTemplate  = processTemplateMapper.selectById(id);
        processTemplate.setStatus("1");
        processTemplateMapper.updateById(processTemplate);
        //优先发布在线流程设计
        if(!StringUtils.isEmpty(processTemplate.getProcessDefinitionPath())) {
            processService.deployByZip(processTemplate.getProcessDefinitionPath());
        }
    }
}
