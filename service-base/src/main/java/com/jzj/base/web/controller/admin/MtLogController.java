package com.jzj.base.web.controller.admin;

import com.jzj.base.web.pojo.entity.MtLog;
import com.jzj.base.web.service.MtLogService;
import com.jzj.common.annotation.Log;
import com.jzj.common.controller.BaseController;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.common.utils.result.R;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * i茅台执行日志Controller
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@RestController
@RequestMapping("/mt/log")
public class MtLogController extends BaseController {

    @Autowired
    private MtLogService mtLogService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('mt.log.list')")
    @GetMapping("/list")
    public TableDataInfo list(MtLog mtLog) {
        startPage();
        List<MtLog> list = mtLogService.selectMtLogList(mtLog);
        return getDataTable(list);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('mt.log.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(mtLogService.selectMtLogById(id));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('mt.log.remove')")
    @Log(title = "i茅台执行日志", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids) {
        return toAjax(mtLogService.deleteMtLogByIds(ids));
    }

}
