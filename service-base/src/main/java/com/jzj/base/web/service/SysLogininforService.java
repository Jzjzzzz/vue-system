package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.SysLogininfor;

/**
 * <p>
 * 系统访问记录 服务类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
public interface SysLogininforService extends IService<SysLogininfor> {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);
}
