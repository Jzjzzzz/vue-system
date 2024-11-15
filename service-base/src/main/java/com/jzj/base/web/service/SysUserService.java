package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.vo.User;
import com.jzj.base.web.pojo.vo.UserUpdateVo;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
public interface SysUserService extends IService<SysUser>, UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String s) throws UsernameNotFoundException;

    /**
     * 分页查询
     *
     * @param sysUser 查询参数
     * @return 列表
     */
    List<User> pageList(SysUser sysUser);

    /**
     * 新增用户表
     *
     * @param sysUser 实体
     * @return 成功条数
     */
    int add(SysUser sysUser);

    /**
     * 根据id查询单条记录
     *
     * @param id id
     */
    SysUser selectById(String id);

    /**
     * 根据用户名查询单挑记录
     * @param username 用户名
     */
    SysUser selectByUserName(String username);

    /**
     * 修改用户表
     *
     * @param sysUser 实体
     * @return 成功条数
     */
    int modify(SysUser sysUser);

    /**
     * 批量删除用户表
     *
     * @param ids id数组
     * @return 成功条数
     */
    int deleteByIds(List<String> ids, HttpServletRequest request);

    /**
     * 获取用户信息
     *
     * @return 返回用户信息
     */
    Map<String, Object> info(String username);

    int updateUser(UserUpdateVo vo);

    /**
     * 强制下线用户
     * @param id
     * @return
     */
    boolean offLine(String id);

    List<SysUser> getLine();
}
