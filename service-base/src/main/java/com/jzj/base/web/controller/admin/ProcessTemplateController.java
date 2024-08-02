package com.jzj.base.web.controller.admin;

import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import com.jzj.base.web.controller.BaseController;
import com.jzj.base.web.pojo.entity.ProcessTemplate;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.page.TableDataInfo;
import com.jzj.base.web.service.ProcessTemplateService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


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

    @Log(title = "审批模板", businessType = BusinessType.UPLOAD)
    @ApiOperation("上传流程定义")
    @PostMapping("/uploadProcessDefinition")
    public R uploadProcessDefinition(MultipartFile file){
        try {
            String path = new File(ResourceUtils.getURL("classpath:").getPath()).getAbsolutePath();
            String fileName = file.getOriginalFilename();
            //上传目录
            File tempFile = new File(path + "/processes/");
            if (!tempFile.exists()) {
                tempFile.mkdirs();
            }
            // 创建空文件用于写入文件
            File imageFile = new File(path + "/processes/" + fileName);
            file.transferTo(imageFile);
            Map<String, Object> map = new HashMap<>();
            map.put("processDefinitionPath", "processes/" + fileName);
            if (fileName != null) {
                map.put("processDefinitionKey", fileName.substring(0, fileName.lastIndexOf(".")));
            }
            return R.ok(map);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @ApiOperation(value = "发布")
    @GetMapping("/publish/{id}")
    public R publish(@PathVariable String id){
        processTemplateService.publish(id);
        return success();
    }
}
