package com.jzj.base.web.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.result.BusinessException;
import com.jzj.base.utils.sign.SecurityUtils;
import com.jzj.base.web.mapper.ProcessMapper;
import com.jzj.base.web.mapper.ProcessRecordMapper;
import com.jzj.base.web.mapper.ProcessTemplateMapper;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.entity.ProcessRecord;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.vo.*;
import com.jzj.base.web.service.ProcessRecordService;
import com.jzj.base.web.service.ProcessService;
import com.jzj.base.web.service.SysUserService;
import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.EndEvent;
import org.activiti.bpmn.model.FlowNode;
import org.activiti.bpmn.model.SequenceFlow;
import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;
import java.util.stream.Collectors;
import java.util.zip.ZipInputStream;

/**
 * 审批类型Service业务层处理
 * 
 * @author Jzj
 * @date 2024-08-01
 */
@Service
public class ProcessServiceImpl extends ServiceImpl<ProcessMapper,Process> implements ProcessService {
    @Autowired
    private ProcessMapper processMapper;

    @Autowired
    private ProcessTemplateMapper processTemplateMapper;

    @Autowired
    private RepositoryService repositoryService;

    @Autowired
    private RuntimeService runtimeService;

    @Autowired
    private TaskService taskService;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private ProcessRecordService processRecordService;

    @Autowired
    private ProcessRecordMapper processRecordMapper;

    @Autowired
    private HistoryService historyService;


    /**
     * 查询审批类型
     * 
     * @param id 审批类型主键
     * @return 审批类型
     */
    @Override
    public Process selectProcessById(String id) {
        return processMapper.selectById(id);
    }

    /**
     * 查询审批类型列表
     * 
     * @param process 审批类型
     * @return 审批类型
     */
    @Override
    public List<Process> selectProcessList(Process process) {
        return processMapper.selectProcessList(process);
    }

    /**
     * 修改审批类型
     * 
     * @param process 审批类型
     * @return 结果
     */
    @Override
    public int updateProcess(Process process) {
        return processMapper.updateById(process);
    }

    /**
     * 批量删除审批类型
     * 
     * @param ids 需要删除的审批类型主键
     * @return 结果
     */
    @Override
    public int deleteProcessByIds(String[] ids) {
        //删除流程表数据
        List<Process> list = processMapper.selectBatchIds(Arrays.asList(ids));
        list.forEach(s->{
            if(s.getStatus().equals("1")){
                runtimeService.deleteProcessInstance(s.getProcessInstanceId(),"");
            }
            processRecordMapper.delete(new QueryWrapper<ProcessRecord>().eq("process_id",s.getId()));
        });
        return processMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 部署流程定义
     */
    @Override
    public void deployByZip(ProcessTemplate processTemplate) {
        // 定义zip输入流
        InputStream inputStream  = this.getClass()
                .getClassLoader()
                .getResourceAsStream(processTemplate.getProcessDefinitionPath());
        if(inputStream == null) throw new BusinessException("当前数据异常,请联系管理员!");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //流程部署
        Deployment deploy = repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
        processTemplate.setProcessModelId(deploy.getId());
        processTemplate.setStatus("1");
        processTemplateMapper.updateById(processTemplate);
    }

    /**
     * 查询所有分类和对应模板
     */
    @Override
    public Map<String, List<ProcessTypeVo>> findProcessType() {
        return processMapper.findProcessType()
                .stream().collect(Collectors.groupingBy(ProcessTypeVo::getDictLabel));
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
        process.setProcessType(template.getProcessType());
        process.setUserName(user.getUsername());
        process.setUserId(user.getId());
        process.setFormValues(form.getFormValues());
        process.setProcessTemplateName(template.getName());
        process.setTitle(user.getUsername() + "发起" + template.getName() + "申请");
        process.setStatus("1");
        processMapper.insert(process);
        //流程参数
        Map<String, Object> variables = new HashMap<>();
        //将表单数据放入流程实例中
        JSONObject jsonObject = JSON.parseObject(process.getFormValues());
        JSONObject formData = jsonObject.getJSONObject("formData");
        Map<String, Object> map = new HashMap<>(formData);
        variables.put("data", map);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey(
                template.getProcessDefinitionKey(),
                process.getId(),
                variables);
        //业务表关联当前流程实例id
        String processInstanceId = processInstance.getId();
        process.setProcessInstanceId(processInstanceId);
        //计算下一个审批人，可能有多个（并行审批）
        nextPerson(process, 1);
        //审核记录
        processRecordService.record(process.getId(), 1, "发起申请");
    }

    @Override
    public Map<String, Object> show(String id) {
        Process process = this.getById(id);
        List<ProcessRecord> processRecordList = processRecordService.list(new LambdaQueryWrapper<ProcessRecord>().eq(ProcessRecord::getProcessId, id));
        ProcessTemplate processTemplate = processTemplateMapper.selectById(process.getProcessTemplateId());
        Map<String, Object> map = new HashMap<>();
        map.put("process", process);
        map.put("processRecordList", processRecordList);
        map.put("processTemplate", processTemplate);
        //计算当前用户是否可以审批，能够查看详情的用户不是都能审批，审批后也不能重复审批
        boolean isApprove = false;
        List<Task> taskList = this.getCurrentTaskList(process.getProcessInstanceId());
        if (!taskList.isEmpty()) {
            for(Task task : taskList) {
                if(task.getAssignee().equals(SecurityUtils.getUserName())) {
                    isApprove = true;
                }
            }
        }
        map.put("isApprove", isApprove);
        return map;
    }

    @Override
    public IPage<ProcessVo> find(ProcessQuery pageParam) {
        List<ProcessVo> processList = new ArrayList<>();
        String type = pageParam.getType();
        long totalCount = 0L;
        if("pending".equals(type)){
            //待处理
            totalCount = findPending(pageParam, processList);
        } else if("process".equals(type)){
            //已处理
            totalCount = findProcessed(pageParam, processList);
        } else {
            pageParam.setUserName(SecurityUtils.getUserName());
            return processMapper.findInit(
                    new Page<>(pageParam.getPageNum(), pageParam.getPageSize()),pageParam);
        }

        IPage<ProcessVo> page = new Page<>(pageParam.getPageNum(), pageParam.getPageSize(), totalCount);
        page.setRecords(processList);
        return page;
    }

    /**
     * 已审批列表
     */
    private long findProcessed(ProcessQuery pageParam, List<ProcessVo> processList){
        HistoricTaskInstanceQuery query = historyService.createHistoricTaskInstanceQuery()
                .taskAssignee(SecurityUtils.getUserName())
                .finished()
                .orderByTaskCreateTime().desc();
        List<HistoricTaskInstance> list = query.listPage((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize());
        long totalCount = query.count();
        for (HistoricTaskInstance item : list) {
            String processInstanceId = item.getProcessInstanceId();
            Process process = processMapper.selectOne(new LambdaQueryWrapper<Process>().eq(Process::getProcessInstanceId, processInstanceId));
            if(process == null) continue;
            ProcessVo processVo = new ProcessVo();
            BeanUtils.copyProperties(process, processVo);
            processVo.setTaskId("0");
            processList.add(processVo);
        }
        return totalCount;
    }

    /**
     * 待审批列表
     */
    private long findPending(ProcessQuery pageParam, List<ProcessVo> processList) {
        // 根据当前人的ID查询
        TaskQuery query = taskService.createTaskQuery().taskAssignee(SecurityUtils.getUserName()).orderByTaskCreateTime().desc();
        List<Task> list = query.listPage((pageParam.getPageNum() - 1) * pageParam.getPageSize(), pageParam.getPageSize());
        long totalCount = query.count();
        // 根据流程的业务ID查询实体并关联
        for (Task item : list) {
            String processInstanceId = item.getProcessInstanceId();
            ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();
            if (processInstance == null) continue;
            // 业务key
            String businessKey = processInstance.getBusinessKey();
            if (businessKey == null) continue;
            Process process = this.getById(businessKey);
            ProcessVo processVo = new ProcessVo();
            BeanUtils.copyProperties(process, processVo);
            processVo.setTaskId(item.getId());
            processList.add(processVo);
        }
        return totalCount;
    }

    @Override
    public void approve(ApprovalVo approvalVo) {
        String taskId = approvalVo.getTaskId();
        Integer status = approvalVo.getStatus();
        String description = "";
        Map<String, Object> variables = taskService.getVariables(taskId);
        for (Map.Entry<String, Object> entry : variables.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue());
        }
        if(1==status){
            //已通过
            Map<String, Object> map = new HashMap<>();
            taskService.complete(taskId,map);
            description = "已通过";
        } else {
            //驳回
            endTime(taskId);
            description = "驳回";
        }
        processRecordService.record(approvalVo.getProcessId(), approvalVo.getStatus(), description);
        //计算下一个审批人
        Process process = processMapper.selectById(approvalVo.getProcessId());
        nextPerson(process, status);
    }

    /**
     * 下一个审批人
     */
    private void nextPerson(Process process, Integer status) {
        List<Task> taskList = getCurrentTaskList(process.getProcessInstanceId());
        if(!taskList.isEmpty()){
            List<String> assigneeList = new ArrayList<>();
            for (Task task : taskList) {
                SysUser user = sysUserService.selectByUserName(task.getAssignee());
                assigneeList.add(user.getUsername());
                //TODO 推送消息给下一个审批人
            }
            process.setDescription("等待" + StringUtils.join(assigneeList.toArray(), ",") + "审批");
            process.setStatus("1");
        } else {
            //当开启任务的人也是最后一个人时,默认审批完成
            if(1 == status) {
                process.setDescription("审批完成（同意）");
                process.setStatus("2");
            } else {
                process.setDescription("审批完成（拒绝）");
                process.setStatus("-1");
            }
        }
        processMapper.updateById(process);
    }

    /**
     * 驳回任务
     * @param taskId 当前任务id
     */
    private void endTime(String taskId) {
        //当前任务
        Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
        BpmnModel bpmnModel = repositoryService.getBpmnModel(task.getProcessDefinitionId());
        List<EndEvent> endEventList  = bpmnModel.getMainProcess().findFlowElementsOfType(EndEvent.class);
        // 并行任务可能为null
        if(endEventList.isEmpty()){
            return;
        }
        FlowNode endFlowNode = (FlowNode) endEventList.get(0);
        FlowNode currentFlowNode = (FlowNode) bpmnModel.getMainProcess().getFlowElement(task.getTaskDefinitionKey());
        //  临时保存当前活动的原始方向
        List originalSequenceFlowList = new ArrayList<>();
        originalSequenceFlowList.addAll(currentFlowNode.getOutgoingFlows());
        //  清理活动方向
        currentFlowNode.getOutgoingFlows().clear();

        //  建立新方向
        SequenceFlow newSequenceFlow = new SequenceFlow();
        newSequenceFlow.setId("newSequenceFlowId");
        newSequenceFlow.setSourceFlowElement(currentFlowNode);
        newSequenceFlow.setTargetFlowElement(endFlowNode);
        List newSequenceFlowList = new ArrayList<>();
        newSequenceFlowList.add(newSequenceFlow);
        //  当前节点指向新的方向
        currentFlowNode.setOutgoingFlows(newSequenceFlowList);

        //  完成当前任务
        taskService.complete(task.getId());
    }

    /**
     * 获取当前任务列表
     */
    private List<Task> getCurrentTaskList(String processInstanceId){
        return taskService.createTaskQuery().processInstanceId(processInstanceId).list();
    }
}
