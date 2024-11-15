package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.base.utils.sign.MenuUtils;
import com.jzj.base.web.mapper.SysMenuMapper;
import com.jzj.base.web.mapper.SysRoleMenuMapper;
import com.jzj.base.web.mapper.SysUserMapper;
import com.jzj.base.web.pojo.entity.SysMenu;
import com.jzj.base.web.pojo.entity.SysRoleMenu;
import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.vo.AssignMenuVo;
import com.jzj.base.web.pojo.vo.MetaVo;
import com.jzj.base.web.pojo.vo.RouterVo;
import com.jzj.base.web.service.SysMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 菜单表 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2024-05-07
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    private SysMenuMapper sysMenuMapper;

    @Autowired
    private SysRoleMenuMapper sysRoleMenuMapper;

    @Autowired
    private SysUserMapper sysUserMapper;

    @Override
    public List<SysMenu> pageList(SysMenu sysMenu) {
        List<SysMenu> list = sysMenuMapper.getPageList(sysMenu);
        return list;
    }

    @Override
    public int add(SysMenu sysMenu) {
        return sysMenuMapper.insert(sysMenu);
    }

    @Override
    public SysMenu selectById(String id) {
        return sysMenuMapper.selectById(id);
    }

    @Override
    public int modify(SysMenu sysMenu) {
        return sysMenuMapper.updateById(sysMenu);
    }

    @Override
    public int deleteByIds(List<String> ids, HttpServletRequest request) {
        return sysMenuMapper.deleteBatchIds(ids);
    }

    @Override
    public List<SysMenu> findNodes() {
        //全部权限列表
        List<SysMenu> sysMenuList = this.list();
        if (CollectionUtils.isEmpty(sysMenuList)) return null;

        //构建树形数据
        return MenuUtils.buildTree(sysMenuList);
    }

    @Override
    public List<SysMenu> findSysMenuByRoleId(String roleId) {
        //全部权限列表
        List<SysMenu> allSysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1));

        //根据角色id获取角色权限
        List<SysRoleMenu> sysRoleMenuList = sysRoleMenuMapper.selectList(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, roleId));
        //转换给角色id与角色权限对应Map对象
        List<String> menuIdList = sysRoleMenuList.stream().map(SysRoleMenu::getMenuId).collect(Collectors.toList());

        allSysMenuList.forEach(permission -> {
            permission.setSelect(menuIdList.contains(permission.getId()));
        });
        return MenuUtils.buildTree(allSysMenuList);
    }

    @Transactional
    @Override
    public boolean doAssign(AssignMenuVo assignMenuVo) {
        try {
            sysRoleMenuMapper.delete(new LambdaQueryWrapper<SysRoleMenu>().eq(SysRoleMenu::getRoleId, assignMenuVo.getRoleId()));
            for (String menuId : assignMenuVo.getMenuIdList()) {
                if (StringUtils.isEmpty(menuId)) continue;
                SysRoleMenu rolePermission = new SysRoleMenu();
                rolePermission.setRoleId(assignMenuVo.getRoleId());
                rolePermission.setMenuId(menuId);
                sysRoleMenuMapper.insert(rolePermission);
            }
            return true;
        } catch (Exception e) {
            log.error("分配角色权限出错", e);
            return false;
        }
    }

    @Override
    public List<RouterVo> findUserMenuList(String userId) {
        List<SysMenu> sysMenuList = null;
        //超级管理员
        SysUser user = sysUserMapper.selectById(userId);

        if(UserConstants.IS_SUPER.equals(user.getIsSuper())) {
            sysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1).orderByAsc(SysMenu::getSortValue));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }
        //构建树形数据
        List<SysMenu> sysMenuTreeList = MenuUtils.buildTree(sysMenuList);

        return this.buildMenus(sysMenuTreeList);
    }

    private List<RouterVo> buildMenus(List<SysMenu> menus) {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus) {
            RouterVo router = new RouterVo();
            router.setHidden(false);
            router.setAlwaysShow(false);
            router.setPath(getRouterPath(menu));
            router.setComponent(menu.getComponent());
            router.setMeta(new MetaVo(menu.getName(), menu.getIcon()));
            List<SysMenu> children = menu.getChildren();
            //如果当前是菜单，需将按钮对应的路由加载出来，如：“角色授权”按钮对应的路由在“系统管理”下面
            if (menu.getType().equals("1")) {
                List<SysMenu> hiddenMenuList = children.stream().filter(item -> !org.springframework.util.StringUtils.isEmpty(item.getComponent())).collect(Collectors.toList());
                for (SysMenu hiddenMenu : hiddenMenuList) {
                    RouterVo hiddenRouter = new RouterVo();
                    hiddenRouter.setHidden(true);
                    hiddenRouter.setAlwaysShow(false);
                    hiddenRouter.setPath(getRouterPath(hiddenMenu));
                    hiddenRouter.setComponent(hiddenMenu.getComponent());
                    hiddenRouter.setMeta(new MetaVo(hiddenMenu.getName(), hiddenMenu.getIcon()));
                    routers.add(hiddenRouter);
                }
            } else {
                if (!CollectionUtils.isEmpty(children)) {
                    router.setAlwaysShow(true);
                    router.setChildren(buildMenus(children));
                }
            }
            routers.add(router);
        }
        return routers;
    }

    public String getRouterPath(SysMenu menu) {
        String routerPath = "/" + menu.getPath();
        if (!menu.getParentId().equals("0")) {
            routerPath = menu.getPath();
        }
        return routerPath;
    }

    @Override
    public List<String> findUserPermsList(String userId) {
        List<SysMenu> sysMenuList = null;
        //超级管理员
        //超级管理员
        SysUser user = sysUserMapper.selectById(userId);

        if(UserConstants.IS_SUPER.equals(user.getIsSuper())) {
            sysMenuList = this.list(new LambdaQueryWrapper<SysMenu>().eq(SysMenu::getStatus, 1));
        } else {
            sysMenuList = sysMenuMapper.findListByUserId(userId);
        }
        return sysMenuList.stream().filter(item -> item.getType().equals("2")).map(item -> item.getPerms()).filter(perms->perms!=null && !perms.isEmpty()).collect(Collectors.toList());
    }
}
