package com.jzj.base.web.controller.admin;


import com.jzj.common.annotation.Log;
import com.jzj.base.utils.constant.UserConstants;
import com.jzj.common.utils.result.R;
import com.jzj.common.utils.StringUtils;
import com.jzj.common.controller.BaseController;
import com.jzj.base.web.pojo.entity.SysDictData;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.base.web.service.SysDictDataService;
import com.jzj.base.web.service.SysDictTypeService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 字典数据表 前端控制器
 * </p>
 *
 * @author Jzj
 * @since 2022-07-08
 */
@RestController
@RequestMapping("/system/dict/data")
public class SysDictDataController extends BaseController {
    @Autowired
    private SysDictDataService dictDataService;

    @Autowired
    private SysDictTypeService dictTypeService;

    @GetMapping("/list")
    @PreAuthorize("hasAuthority('btn.dict.list')")
    public TableDataInfo list(SysDictData dictData) {
        startPage();
        List<SysDictData> list = dictDataService.selectDictDataList(dictData);
        return getDataTable(list);
    }

    @ApiOperation("根据字典类型查询字典数据信息")
    @GetMapping(value = "/type/{dictType}")
    public R dictType(@PathVariable String dictType) {
        List<SysDictData> data = dictTypeService.selectDictDataByType(dictType);
        if (StringUtils.isNull(data)) {
            data = new ArrayList<>();
        }
        return R.ok(data);
    }

    @ApiOperation("查询字典数据详细")
    @GetMapping(value = "/{dictCode}")
    public R getInfo(@PathVariable Long dictCode) {
        return R.ok(dictDataService.selectDictDataById(dictCode));
    }

    @Log(title = "字典类型", businessType = BusinessType.INSERT)
    @ApiOperation("新增字典类型")
    @PostMapping
    @PreAuthorize("hasAuthority('btn.dict.add')")
    public R add(@Validated @RequestBody SysDictData dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictDataService.checkDictDataUnique(dict))) {
            return R.error("新增字典类型'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        return toAjax(dictDataService.insertDictData(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.UPDATE)
    @ApiOperation("修改保存字典类型")
    @PutMapping
    @PreAuthorize("hasAuthority('btn.dict.edit')")
    public R edit(@Validated @RequestBody SysDictData dict) {
        if (UserConstants.NOT_UNIQUE.equals(dictDataService.checkDictDataUnique(dict))) {
            return R.error("修改字典类型'" + dict.getDictValue() + "'失败，字典键值已存在");
        }
        return toAjax(dictDataService.updateDictData(dict));
    }

    @Log(title = "字典类型", businessType = BusinessType.DELETE)
    @ApiOperation("删除字典类型")
    @PreAuthorize("hasAuthority('btn.dict.del')")
    @DeleteMapping("/{dictCodes}")
    public R remove(@PathVariable Long[] dictCodes) {
        return toAjax(dictDataService.deleteDictDataByIds(dictCodes));
    }
}

