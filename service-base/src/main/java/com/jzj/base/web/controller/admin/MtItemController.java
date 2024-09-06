package com.jzj.base.web.controller.admin;

import com.jzj.base.web.pojo.entity.MtItem;
import com.jzj.base.web.service.MtItemService;
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
 * i茅台预约商品Controller
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@RestController
@RequestMapping("/mt/item")
public class MtItemController extends BaseController {

    @Autowired
    private MtItemService mtItemService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('mt.item.list')")
    @GetMapping("/list")
    public TableDataInfo list(MtItem mtItem) {
        startPage();
        List<MtItem> list = mtItemService.selectMtItemList(mtItem);
        return getDataTable(list);
    }

    @ApiOperation("查询I茅台预约商品列列表")
    @GetMapping("/listAll")
    public R list() {
        List<MtItem> iItems = mtItemService.selectList();
        return R.ok(iItems);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('mt.item.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(mtItemService.selectMtItemById(id));
    }

    @ApiOperation("新增")
    @PreAuthorize("hasAuthority('mt.item.add')")
    @Log(title = "I茅台预约商品", businessType = BusinessType.INSERT)
    @PostMapping
    public R add() {
        mtItemService.insertMtItem();
        return R.ok();
    }
}
