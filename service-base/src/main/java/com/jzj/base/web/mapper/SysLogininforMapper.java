package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.SysLogininfor;

/**
 * <p>
 * 系统访问记录 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
public interface SysLogininforMapper extends BaseMapper<SysLogininfor> {

    /**
     * 新增系统登录日志
     *
     * @param logininfor 访问日志对象
     */
    void insertLogininfor(SysLogininfor logininfor);
}
