package com.jzj.base.web.controller.admin;

import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import com.jzj.base.web.controller.BaseController;
import com.jzj.base.web.pojo.entity.Process;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.page.TableDataInfo;
import com.jzj.base.web.service.ProcessService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * 审批类型Controller
 * 
 * @author Jzj
 * @date 2024-08-01
 */
@RestController
@RequestMapping("/oa/process")
public class ProcessController extends BaseController {

    @Autowired
    private ProcessService processService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('oa.process.list')")
    @GetMapping("/list")
    public TableDataInfo list(Process process) {
        startPage();
        List<Process> list = processService.selectProcessList(process);
        return getDataTable(list);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('oa.process.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(processService.selectProcessById(id));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('oa.process.edit')")
    @Log(title = "审批类型", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody Process process) {
        return toAjax(processService.updateProcess(process));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('oa.process.remove')")
    @Log(title = "审批类型", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids) {
        return toAjax(processService.deleteProcessByIds(ids));
    }

    @ApiOperation(value = "获取全部审批分类及模板")
    @GetMapping("findProcessType")
    public R findProcessType() {
        return R.ok(processService.findProcessType());
    }
}
