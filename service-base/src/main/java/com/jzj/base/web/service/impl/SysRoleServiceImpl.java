package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.base.web.mapper.SysRoleMapper;
import com.jzj.base.web.mapper.SysRoleMenuMapper;
import com.jzj.base.web.mapper.SysUserMapper;
import com.jzj.base.web.mapper.SysUserRoleMapper;
import com.jzj.base.web.pojo.entity.SysRole;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.entity.SysUserRole;
import com.jzj.base.web.service.SysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-06
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysRoleMapper sysRoleMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysRole> pageList(SysRole sysRole) {
        List<SysRole> list = sysRoleMapper.getPageList(sysRole);
        return list;
    }

    @Override
    public int add(SysRole sysRole) {
        return sysRoleMapper.insert(sysRole);
    }

    @Override
    public SysRole selectById(String id) {
        return sysRoleMapper.selectById(id);
    }

    @Override
    public int modify(SysRole sysRole) {
        return sysRoleMapper.updateById(sysRole);
    }

    @Transactional
    @Override
    public int deleteByIds(List<String> ids, HttpServletRequest request) {
        ids.forEach(id -> {
            //这里既要删除用户角色表关联数据，又要删除角色权限表关联数据
            sysUserRoleMapper.deleteBatchByRoleId(id);
            sysRoleMenuMapper.deleteBatchByRoleId(id);
        });
        return sysRoleMapper.deleteBatchIds(ids);
    }

    @Override
    public Map<String, Object> getRoleListByUserId(String userId) {
        HashMap<String, Object> map = new HashMap<>();
        List<SysRole> roles = sysRoleMapper.selectList(new QueryWrapper<SysRole>().eq("status", 1));
        List<SysUserRole> selectList = sysUserRoleMapper.selectList(new QueryWrapper<SysUserRole>().eq("user_id", userId));
        String[] ids = new String[selectList.size()];
        if (!selectList.isEmpty()) {
            for (int i = 0; i < selectList.size(); i++) {
                ids[i] = selectList.get(i).getRoleId();
            }
        }
        map.put("roles", roles);
        map.put("ids", ids);
        return map;
    }

    @Override
    public List<String> findUserRoleList(String id) {
        List<SysRole> sysRoleList;
        //超级管理员
        SysUser user = sysUserMapper.selectById(id);

        if(UserConstants.IS_SUPER.equals(user.getIsSuper())) {
            sysRoleList = this.list(new LambdaQueryWrapper<SysRole>().eq(SysRole::getStatus, 1));
        } else {
            sysRoleList = sysRoleMapper.findListByUserId(id);
        }
        return sysRoleList.stream().map(SysRole::getRoleCode).collect(Collectors.toList());
    }
}
