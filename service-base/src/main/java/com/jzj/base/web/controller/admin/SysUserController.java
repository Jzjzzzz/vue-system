package com.jzj.base.web.controller.admin;


import com.jzj.base.web.pojo.entity.SysUser;
import com.jzj.base.web.pojo.vo.User;
import com.jzj.base.web.pojo.vo.UserAddRoleVo;
import com.jzj.base.web.service.SysUserRoleService;
import com.jzj.base.web.service.SysUserService;
import com.jzj.common.annotation.Log;
import com.jzj.common.controller.BaseController;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.common.utils.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
@RestController
@RequestMapping("/system/user")
public class SysUserController extends BaseController {

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @ApiOperation("分页列表")
    @GetMapping
    @PreAuthorize("hasAuthority('btn.user.list')")
    public TableDataInfo pageList(SysUser sysUser) {
        startPage();
        List<User> list = sysUserService.pageList(sysUser);
        return getDataTable(list);
    }

    @Log(title = "用户表管理", businessType = BusinessType.INSERT)
    @ApiOperation("新增用户表")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.user.add')")
    public R add(@Validated @RequestBody SysUser sysUser) {
        return toAjax(sysUserService.add(sysUser));
    }

    @ApiOperation("根据用户表id获取用户表详细信息")
    @GetMapping(value = "/{id}")
    @PreAuthorize("hasAuthority('btn.user.list')")
    public R getInfo(@PathVariable String id) {
        return R.ok(sysUserService.selectById(id));
    }

    @ApiOperation("修改用户表")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('btn.user.edit')")
    public R edit(@Validated @RequestBody SysUser sysUser) {
        return toAjax(sysUserService.modify(sysUser));
    }

    @ApiOperation("删除用户表")
    @Log(title = "用户表管理", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    @PreAuthorize("hasAuthority('btn.user.del')")
    public R remove(@PathVariable List<String> ids, HttpServletRequest request) {
        return toAjax(sysUserService.deleteByIds(ids, request));
    }

    @ApiOperation("重置密码")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping("/restPassword")
    @PreAuthorize("hasAuthority('btn.user.edit')")
    public R restPassword(@RequestBody SysUser sysUser) {
        sysUser.setPassword(new BCryptPasswordEncoder().encode(sysUser.getPassword()));
        return toAjax(sysUserService.modify(sysUser));
    }

    @ApiOperation("角色分配")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @PutMapping("/allocationRole")
    @PreAuthorize("hasAuthority('btn.user.edit')")
    public R allocationRole(@RequestBody UserAddRoleVo vo) {
        return toAjax(sysUserRoleService.allocationRole(vo));
    }

    @ApiOperation("强制下线")
    @Log(title = "用户表管理", businessType = BusinessType.UPDATE)
    @GetMapping("/offLine/{id}")
    @PreAuthorize("hasAuthority('btn.user.off')")
    public R offLine(@PathVariable("id") String id) {
        return toAjax(sysUserService.offLine(id));
    }

    @ApiOperation("获取所有在线用户")
    @GetMapping("/getLine")
    public R getLine() {
        return R.ok(sysUserService.getLine());
    }
}

