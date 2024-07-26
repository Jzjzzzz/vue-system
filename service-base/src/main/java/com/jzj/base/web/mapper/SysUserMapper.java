package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.excel.UserExcel;
import com.jzj.base.web.pojo.vo.User;

import java.util.List;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
public interface SysUserMapper extends BaseMapper<SysUser> {
    List<User> getPageList(SysUser sysUser);

    /**
     * 导出查询列表
     * @param sysUser 查询条件
     * @return 列表
     */
    List<UserExcel> getList(SysUser sysUser);
}
