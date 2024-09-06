package com.jzj.base.web.controller.admin;

import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.service.MtShopService;
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
 * i茅台商店Controller
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@RestController
@RequestMapping("/mt/shop")
public class MtShopController extends BaseController {

    @Autowired
    private MtShopService mtShopService;

    @ApiOperation("分页列表")
    @PreAuthorize("hasAuthority('mt.shop.list')")
    @GetMapping("/list")
    public TableDataInfo list(MtShop mtShop) {
        startPage();
        List<MtShop> list = mtShopService.selectMtShopList(mtShop);
        return getDataTable(list);
    }

    @ApiOperation("根据id获取详细信息")
    @PreAuthorize("hasAuthority('mt.shop.list')")
    @GetMapping(value = "/{id}")
    public R getInfo(@PathVariable("id") String id) {
        return R.ok(mtShopService.selectMtShopById(id));
    }

    @ApiOperation("新增")
    @PreAuthorize("hasAuthority('mt.shop.add')")
    @Log(title = "i茅台商店", businessType = BusinessType.INSERT)
    @PostMapping
    public R add() {
        mtShopService.insertMtShop();
        return R.ok();
    }

}
