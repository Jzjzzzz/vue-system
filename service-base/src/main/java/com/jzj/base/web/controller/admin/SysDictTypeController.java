package com.jzj.base.web.controller.admin;


import com.jzj.common.annotation.Log;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.common.utils.result.R;
import com.jzj.common.controller.BaseController;
import com.jzj.base.web.pojo.entity.SysDictType;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.base.web.service.SysDictTypeService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 字典类型表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@Api(tags = "字典类型管理")
@RestController
@RequestMapping("/system/dict/type")
public class SysDictTypeController extends BaseController {

    @Autowired
    private SysDictTypeService dictTypeService;

    @ApiOperation("分页列表")
    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.dict.list')")
    public TableDataInfo list(SysDictType dictType) {
        startPage();
        List<SysDictType> list = dictTypeService.selectDictTypeList(dictType);
        return getDataTable(list);
    }

    @ApiOperation("查询字典类型详细")
    @GetMapping(value = "/{dictId}")
    @PreAuthorize("hasAuthority('btn.dict.list')")
    public R getInfo(@PathVariable Long dictId) {
        return R.ok(dictTypeService.selectDictTypeById(dictId));
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典类型")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.dict.add')")
    public R add(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return R.error("新增字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.insertDictType(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ApiOperation("修改字典类型")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.dict.update')")
    public R edit(@Validated @RequestBody SysDictType dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictTypeService.checkDictTypeUnique(dict))) {
            return R.error("修改字典'" + dict.getDictName() + "'失败，字典类型已存在");
        }
        return toAjax(dictTypeService.updateDictType(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("删除字典类型")
    @DeleteMapping("/{dictIds}")
    @PreAuthorize("hasAuthority('btn.dict.del')")
    public R remove(@PathVariable Long[] dictIds) {
        return toAjax(dictTypeService.deleteDictTypeByIds(dictIds));
    }

    @Log(title = "字典类型", businessType = BusinessType.CLEAN)
    @ApiOperation("刷新字典缓存")
    @DeleteMapping("/refreshCache")
    @PreAuthorize("hasAuthority('btn.dict.edit')")
    public R refreshCache() {
        dictTypeService.resetDictCache();
        return success();
    }

    @ApiOperation("获取字典选择框列表")
    @GetMapping("/optionselect")
    @PreAuthorize("hasAuthority('btn.dict.list')")
    public R optionselect() {
        return R.ok(dictTypeService.selectDictTypeAll());
    }

}

