package com.jzj.base.web.mapper;

import java.util.List;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * 审批模板Mapper接口
 * 
 * @author Jzj
 * @date 2024-07-30
 */
public interface ProcessTemplateMapper extends BaseMapper<ProcessTemplate>
{
    /**
     * 查询审批模板列表
     * 
     * @param processTemplate 审批模板
     * @return 审批模板集合
     */
     List<ProcessTemplate> selectProcessTemplateList(ProcessTemplate processTemplate);

}
