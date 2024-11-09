package com.jzj.base.web.controller;

import com.jzj.base.factory.ExcelFactory;
import com.jzj.base.listener.base.BaseExcelListener;
import com.jzj.common.utils.result.BusinessException;
import com.jzj.common.utils.result.R;
import com.jzj.common.utils.result.ResponseEnum;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * <p>
 *
 * </p>
 *
 * @author Jzj
 * @since 2024/9/1 02:42
 */
@RestController
@RequestMapping("api/excel")
@Api("excel导入控制器")
public class ExcelController {

    @ApiOperation("Excel批量导入")
    @PostMapping("/import")
    public R batchImport(@ApiParam(value = "Excel文件", required = true) @RequestParam("file") MultipartFile file,String code) {
        try {
            BaseExcelListener listener = ExcelFactory.getInvokeStrategy(code);
            listener.read(file.getInputStream());
            return R.ok().message("批量导入成功");
        } catch (Exception e) {
            throw new BusinessException(ResponseEnum.UPLOAD_ERROR, e);
        }
    }
}
