package com.jzj.base.web.controller;

import java.util.List;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import io.swagger.annotations.ApiOperation;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.service.ProcessTemplateService;
import com.jzj.base.web.pojo.page.TableDataInfo;


/**
 * 审批模板Controller
 * 
 * @author Jzj
 * @date 2024-07-30
 */
@RestController
@RequestMapping("/oa/template")
public class ProcessTemplateController extends BaseController {

    @Autowired
    private ProcessTemplateService processTemplateService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('oa.template.list')")
    @GetMapping("/list")
    public TableDataInfo list(ProcessTemplate processTemplate) {
        startPage();
        List<ProcessTemplate> list = processTemplateService.selectProcessTemplateList(processTemplate);
        return getDataTable(list);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('oa.template.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(processTemplateService.selectProcessTemplateById(id));
    }

    @ApiOperation("新增")
    @PreAuthorize("hasAuthority('oa.template.add')")
    @Log(title = "审批模板", businessType = BusinessType.INSERT)
    @PostMapping
    public R add(@RequestBody ProcessTemplate processTemplate) {
        return toAjax(processTemplateService.insertProcessTemplate(processTemplate));
    }

    @ApiOperation("修改")
    @PreAuthorize("hasAuthority('oa.template.edit')")
    @Log(title = "审批模板", businessType = BusinessType.UPDATE)
    @PutMapping
    public R edit(@RequestBody ProcessTemplate processTemplate) {
        return toAjax(processTemplateService.updateProcessTemplate(processTemplate));
    }

    @ApiOperation("删除")
    @PreAuthorize("hasAuthority('oa.template.remove')")
    @Log(title = "审批模板", businessType = BusinessType.DELETE)
    @DeleteMapping("/{ids}")
    public R remove(@PathVariable String[] ids) {
        return toAjax(processTemplateService.deleteProcessTemplateByIds(ids));
    }

}
