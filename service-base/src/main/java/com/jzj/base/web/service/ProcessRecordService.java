package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.ProcessRecord;
/**
 * 审批记录Service接口
 * 
 * @author Jzj
 * @date 2024-08-05
 */
public interface ProcessRecordService extends IService<ProcessRecord> {
    /**
     * 记录数据
     */
    void record(String processId, Integer status, String description);
}
