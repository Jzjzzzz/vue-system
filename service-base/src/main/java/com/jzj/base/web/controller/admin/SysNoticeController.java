package com.jzj.base.web.controller.admin;


import com.jzj.common.annotation.Log;
import com.jzj.common.utils.result.R;
import com.jzj.common.controller.BaseController;
import com.jzj.base.web.pojo.entity.SysNotice;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.base.web.service.SysNoticeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 通知公告表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/system/notice")
public class SysNoticeController extends BaseController {

    @Autowired
    private SysNoticeService noticeService;

    @ApiOperation("获取通知公告列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.notice.list')")
    public TableDataInfo list(SysNotice notice) {
        startPage();
        List<SysNotice> list = noticeService.selectNoticeList(notice);
        return getDataTable(list);
    }

    @ApiOperation("根据通知公告编号获取详细信息")
    @GetMapping(value = "/{noticeId}")
    @PreAuthorize("hasAuthority('btn.notice.list')")
    public R getInfo(@PathVariable String noticeId) {
        return R.ok(noticeService.selectNoticeById(noticeId));
    }

    @ApiOperation("新增通知公告")
    @Log(title = "通知公告", businessType = BusinessType.INSERT)
    @PostMapping
    @PreAuthorize("hasAuthority('btn.notice.add')")
    public R add(@Validated @RequestBody SysNotice notice) {
        return toAjax(noticeService.insertNotice(notice));
    }

    @ApiOperation("修改通知公告")
    @Log(title = "通知公告", businessType = BusinessType.UPDATE)
    @PutMapping
    @PreAuthorize("hasAuthority('btn.notice.edit')")
    public R edit(@Validated @RequestBody SysNotice notice) {
        return toAjax(noticeService.updateNotice(notice));
    }

    @ApiOperation("删除通知公告")
    @Log(title = "通知公告", businessType = BusinessType.DELETE)
    @DeleteMapping("/{noticeIds}")
    @PreAuthorize("hasAuthority('btn.notice.del')")
    public R remove(@PathVariable String[] noticeIds) {
        return toAjax(noticeService.deleteNoticeByIds(noticeIds));
    }
}

