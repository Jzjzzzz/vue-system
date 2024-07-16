package com.jzj.base.web.controller.admin;


import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import com.jzj.base.web.controller.BaseController;
import com.jzj.base.web.pojo.entity.SysOperLog;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.page.TableDataInfo;
import com.jzj.base.web.service.SysOperLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 操作日志记录 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-22
 */
@RestController
@RequestMapping("/monitor/operlog")
public class SysOperLogController extends BaseController {

    @Autowired
    private SysOperLogService operLogService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.operlog.list')")
    public TableDataInfo list(SysOperLog operLog) {
        startPage();
        List<SysOperLog> list = operLogService.selectOperLogList(operLog);
        return getDataTable(list);
    }

    @Log(title = "操作日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{operIds}")
    @PreAuthorize("hasAuthority('btn.operlog.del')")
    public R remove(@PathVariable Long[] operIds) {
        return toAjax(operLogService.deleteOperLogByIds(operIds));
    }

    @Log(title = "操作日志", businessType = BusinessType.CLEAN)
    @DeleteMapping("/clean")
    @PreAuthorize("hasAuthority('btn.operlog.del')")
    public R clean() {
        operLogService.cleanOperLog();
        return R.ok();
    }
}

