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
import com.jzj.base.web.pojo.vo.ProcessTypeVo;
import com.jzj.base.web.service.ProcessService;
import com.jzj.base.web.service.SysUserService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
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
        return processMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 部署流程定义
     */
    @Override
    public void deployByZip(String deployPath) {
        // 定义zip输入流
        InputStream inputStream  = this.getClass()
                .getClassLoader()
                .getResourceAsStream(deployPath);
        if(inputStream == null) throw new BusinessException("当前数据异常,请联系管理员!");
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //流程部署
        repositoryService.createDeployment().addZipInputStream(zipInputStream).deploy();
    }

    /**
     * 查询所有分类和对应模板
     */
    @Override
    public Map<String, List<ProcessTypeVo>> findProcessType() {
        List<ProcessTypeVo> list = processMapper.findProcessType();
        Map<String, List<ProcessTypeVo>> map = list.stream().collect(Collectors.groupingBy(s -> s.getDictLabel()));
        return map;
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
