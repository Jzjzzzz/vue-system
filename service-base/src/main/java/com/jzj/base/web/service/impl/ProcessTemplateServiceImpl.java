package com.jzj.base.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.result.BusinessException;
import com.jzj.base.utils.sign.SecurityUtils;
import com.jzj.base.web.mapper.ProcessMapper;
import com.jzj.base.web.mapper.ProcessTemplateMapper;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.vo.ProcessFormVo;
import com.jzj.base.web.service.ProcessService;
import com.jzj.base.web.service.ProcessTemplateService;
import com.jzj.base.web.service.SysUserService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
    private SysUserService sysUserService;

    @Autowired
    private ProcessService processService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;
    @Autowired
    private ProcessMapper processMapper;

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
     * 删除审批模板信息
     * 
     * @param id 审批模板主键
     * @return 结果
     */
    @Override
    public int deleteProcessTemplateById(String id) {
        return processTemplateMapper.deleteById(id);
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

    /**
     * 开启流程控制
     * @param form 提交表单
     */
    @Override
    public void startUp(ProcessFormVo form) {
        SysUser user = sysUserService.selectByUserName(SecurityUtils.getUserName());
        if(user==null) throw new BusinessException("用户不存在!");
        ProcessTemplate template = processTemplateMapper.selectById(form.getProcessTemplateId());
        Process process = new Process();
        BeanUtils.copyProperties(form, process);
        String workNo = System.currentTimeMillis() + "";
        process.setProcessCode(workNo);
        process.setUserId(user.getId());
        process.setFormValues(form.getFormValues());
        process.setTitle(user.getName() + "发起" + template.getName() + "申请");
        process.setStatus("1");
        processService.save(process);
        //绑定业务id
        String businessKey = String.valueOf(process.getId());
        //流程参数
        Map<String, Object> variables = new HashMap<>();
        //将表单数据放入流程实例中
        JSONObject jsonObject = JSON.parseObject(process.getFormValues());
        JSONObject formData = jsonObject.getJSONObject("formData");
        Map<String, Object> map = new HashMap<>(formData);
        variables.put("data", map);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                template.getProcessDefinitionKey(),
                businessKey,
                variables);
        //业务表关联当前流程实例id
        String processInstanceId = processInstance.getId();
        process.setProcessInstanceId(processInstanceId);
        //计算下一个审批人，可能有多个（并行审批）
        List<Task> taskList = getCurrentTaskList(processInstanceId);
        if(!taskList.isEmpty()){
            List<String> assigneeList = new ArrayList<>();
            for (Task task : taskList) {
                //assignee就是你创建流程图的时候填写的值
                SysUser nextUser = sysUserService.selectByUserName(task.getAssignee());
                assigneeList.add(nextUser.getName());
                // TODO 推送消息给下一个审批人
            }
            process.setDescription("等待" + StringUtils.join(assigneeList.toArray(), ",") + "审批");
        }
        processMapper.updateById(process);
    }

    /**
     * 获取当前任务列表
     */
    private List<Task> getCurrentTaskList(String processInstanceId){
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }
}
