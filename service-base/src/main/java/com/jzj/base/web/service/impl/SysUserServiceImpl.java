package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.security.custom.CustomUser;
import com.jzj.base.utils.constant.CacheConstants;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.utils.result.BusinessException;
import com.jzj.base.utils.sign.MD5Utils;
import com.jzj.base.web.mapper.SysUserMapper;
import com.jzj.base.web.mapper.SysUserRoleMapper;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.excel.UserExcel;
import com.jzj.base.web.pojo.vo.RouterVo;
import com.jzj.base.web.pojo.vo.User;
import com.jzj.base.web.pojo.vo.UserUpdateVo;
import com.jzj.base.web.service.SysMenuService;
import com.jzj.base.web.service.SysRoleService;
import com.jzj.base.web.service.SysUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;

    @Autowired
    private SysMenuService sysMenuService;

    @Autowired
    private SysRoleService sysRoleService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private RedisCache redisCache;

    @Override
    public List<User> pageList(SysUser sysUser) {
        List<User> list = sysUserMapper.getPageList(sysUser);
        return list.stream().peek(s-> s.setOnLine(redisCache.hasKey(CacheConstants.LOGIN_TOKEN_KEY + s.getId()))).collect(Collectors.toList());
    }

    @Override
    public int add(SysUser sysUser) {
        //判断是否唯一
        long usrCount = this.count(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, sysUser.getUsername()));
        if (usrCount > 0) throw new BusinessException("用户名已存在!");
        sysUser.setPassword(MD5Utils.encrypt(sysUser.getPassword()));
        return sysUserMapper.insert(sysUser);
    }

    @Override
    public SysUser selectById(String id) {
        return sysUserMapper.selectById(id);
    }

    @Override
    public int modify(SysUser sysUser) {
        //用户名不允许修改
        sysUser.setUsername(null);
        if("0".equals(sysUser.getStatus())){
            if (UserConstants.IS_SUPER.equals(sysUser.getIsSuper())) throw new BusinessException("超级管理不允许停用!");
        }
        return sysUserMapper.updateById(sysUser);
    }

    @Transactional
    @Override
    public int deleteByIds(List<String> ids, HttpServletRequest request) {
        ids.forEach(id -> {
            SysUser user = sysUserMapper.selectById(id);
            if (UserConstants.IS_SUPER.equals(user.getIsSuper())) throw new BusinessException("不允许删除超级管理员!");
            //删除用户之前先去删除用户角色关联表数据
            sysUserRoleMapper.deleteBatchByUserId(id);
        });
        return sysUserMapper.deleteBatchIds(ids);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));
        if (null == sysUser) {
            throw new BusinessException("账号密码错误!");
        }
        if (sysUser.getStatus().equals("0")) {
            throw new BusinessException("账号已停用!");
        }
        List<String> userPermsList = sysMenuService.findUserPermsList(sysUser.getId());
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (String perm : userPermsList) {
            authorities.add(new SimpleGrantedAuthority(perm.trim()));
        }
        return new CustomUser(sysUser, authorities);
    }

    @Override
    public Map<String, Object> info(String username) {
        Map<String, Object> result = new HashMap<>();
        SysUser sysUser = this.getOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, username));

        //根据用户id获取菜单权限值
        List<RouterVo> routerVoList = sysMenuService.findUserMenuList(sysUser.getId());
        //根据用户id获取用户按钮权限
        List<String> permsList = sysMenuService.findUserPermsList(sysUser.getId());
        //根据用户id获取用户角色
        List<String> roles = sysRoleService.findUserRoleList(sysUser.getId());
        permsList.removeIf(String::isEmpty);
        result.put("name", sysUser.getUsername());
        result.put("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
        result.put("roles", roles);
        result.put("buttons", permsList);
        result.put("routers", routerVoList);
        return result;
    }

    @Override
    public int updateUser(UserUpdateVo vo) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SysUser user = baseMapper.selectOne(new LambdaQueryWrapper<SysUser>().eq(SysUser::getUsername, authentication.getName()));
        if (null == user) throw new BusinessException("用户不存在!");
        if (StringUtils.isNotEmpty(vo.getNewpassword1())) {
            if (!vo.getNewpassword1().equals(vo.getNewpassword2()))
                throw new BusinessException("两次输入的密码不一致!");
            if (StringUtils.isEmpty(vo.getOldpassword())) throw new BusinessException("旧密码不能为空!");
            String oldPassword = MD5Utils.encrypt(vo.getOldpassword());
            if (!oldPassword.equals(user.getPassword())) throw new BusinessException("旧密码错误!");
            user.setPassword(MD5Utils.encrypt(vo.getNewpassword1()));
            return baseMapper.updateById(user);
        }
        return 0;
    }

    @Override
    public boolean offLine(String id) {
        return redisCache.deleteObject(CacheConstants.LOGIN_TOKEN_KEY + id);
    }

    @Override
    public List<UserExcel> export(SysUser sysUser) {
        return baseMapper.getList(sysUser);
    }
}
