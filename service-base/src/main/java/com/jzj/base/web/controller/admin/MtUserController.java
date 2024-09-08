package com.jzj.base.web.controller.admin;

import com.jzj.base.web.pojo.entity.MtUser;
import com.jzj.base.web.service.MtUserService;
import com.jzj.common.annotation.Log;
import com.jzj.common.controller.BaseController;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.common.utils.StringUtils;
import com.jzj.common.utils.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * i茅台用户Controller
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@RestController
@RequestMapping("/mt/user")
public class MtUserController extends BaseController {

    @Autowired
    private MtUserService mtUserService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('mt.user.list')")
    @GetMapping("/list")
    public TableDataInfo list(MtUser mtUser) {
        startPage();
        List<MtUser> list = mtUserService.selectMtUserList(mtUser);
        return getDataTable(list);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('mt.user.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(mtUserService.selectMtUserById(id));
    }

    @ApiOperation("新增")
    @PreAuthorize("hasAuthority('mt.user.add')")
    @Log(title = "i茅台用户", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody MtUser mtUser) {
        return toAjax(mtUserService.insertMtUser(mtUser));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('mt.user.edit')")
    @Log(title = "i茅台用户", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody MtUser mtUser) {
        return toAjax(mtUserService.updateMtUser(mtUser));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('mt.user.remove')")
    @Log(title = "i茅台用户", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids) {
        return toAjax(mtUserService.deleteMtUserByIds(ids));
    }

    @ApiOperation("发送验证码")
    @GetMapping("/sendCode")
    public R sendCode(String mobile, String deviceId) {
        return toAjax(mtUserService.sendCode(mobile, deviceId));
    }

    @ApiOperation("登录")
    @GetMapping("/login")
    public R login(String mobile, String code, String deviceId) {
        return toAjax(mtUserService.login(mobile, code, deviceId));
    }

    /**
     * 预约
     */
    @GetMapping(value = "/reservation", name = "预约")
    public R reservation(String id) {
        MtUser user = mtUserService.getById(id);
        if (user == null) {
            return R.error("用户不存在");
        }
        if (StringUtils.isEmpty(user.getItemCode())) {
            return R.error("商品预约code为空");
        }
        mtUserService.reservation(user);
        return R.ok();
    }

}
