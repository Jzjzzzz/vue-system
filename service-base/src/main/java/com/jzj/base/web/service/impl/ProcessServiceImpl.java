package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.mapper.ProcessMapper;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.vo.ProcessTypeVo;
import com.jzj.base.web.service.ProcessService;
import org.activiti.engine.RepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
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
    private RepositoryService repositoryService;

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
        ZipInputStream zipInputStream = new ZipInputStream(inputStream);
        //流程部署
        repositoryService.createDeployment()
                .addZipInputStream(zipInputStream)
                .deploy();
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


}
