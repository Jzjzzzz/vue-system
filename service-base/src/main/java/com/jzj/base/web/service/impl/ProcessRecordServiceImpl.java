package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.mapper.ProcessRecordMapper;
import com.jzj.base.web.pojo.entity.ProcessRecord;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.service.ProcessRecordService;
import com.jzj.base.web.service.SysUserService;
import com.jzj.common.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 审批记录Service业务层处理
 * 
 * @author Jzj
 * @date 2024-08-05
 */
@Service
public class ProcessRecordServiceImpl extends ServiceImpl<ProcessRecordMapper,ProcessRecord> implements ProcessRecordService {

    @Autowired
    private ProcessRecordMapper processRecordMapper;

    @Autowired
    private SysUserService sysUserService;

    /**
     * 记录数据
     */
    @Override
    public void record(String processId, Integer status, String description) {
        SysUser sysUser = sysUserService.selectByUserName(SecurityUtils.getUserName());
        ProcessRecord processRecord = new ProcessRecord();
        processRecord.setProcessId(processId);
        processRecord.setStatus(status);
        processRecord.setDescription(description);
        processRecord.setOperateUserId(sysUser.getId());
        processRecord.setOperateUser(sysUser.getUsername());
        processRecordMapper.insert(processRecord);
    }
}
