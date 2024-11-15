package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.SysUserRole;
import com.jzj.base.web.pojo.vo.UserAddRoleVo;

/**
 * <p>
 * 用户角色 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06
 */
public interface SysUserRoleService extends IService<SysUserRole> {

    /**
     * 用户分配角色
     *
     * @param vo 用户分配角色
     * @return 分配结果
     */
    boolean allocationRole(UserAddRoleVo vo);
}
