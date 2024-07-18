package com.jzj.base.web.controller.admin;

import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import com.jzj.base.utils.text.Convert;
import com.jzj.base.web.controller.BaseController;
import com.jzj.base.web.pojo.entity.GenTable;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.page.TableDataInfo;
import com.jzj.base.web.service.GenTableService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2024/7/16 下午2:59
 * @Version 1.0
 * @Message: 代码生成器控制器
 */
@RestController
@RequestMapping("/tool/gen")
@Slf4j
public class GenController extends BaseController {

    @Autowired
    private GenTableService genTableService;

    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo genList(GenTable genTable)
    {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }


    /**
     * 查询数据库列表
     */
    @GetMapping("/db/list")
    @ResponseBody
    public TableDataInfo dataList(GenTable genTable){
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }


    /**
     * 导入表结构（保存）
     */
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public R importTableSave(String tables)
    {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList, SecurityContextHolder.getContext().getAuthentication().getName());
        return success();
    }

    /**
     * 删除代码生成
     */
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public R remove(@PathVariable Long [] tableIds){
        genTableService.deleteGenTableByIds(tableIds);
        return success();
    }

    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response,@PathVariable("tableName") String tableName) {
        byte [] data =genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    /**
     * 批量生成代码
     */
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    /**
     * 生成zip文件
     */
    private void genCode(HttpServletResponse response, byte[] data) {
        try {
            response.reset();
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.addHeader("Access-Control-Expose-Headers", "Content-Disposition");
            response.setHeader("Content-Disposition", "attachment; filename=\"code.zip\"");
            response.addHeader("Content-Length", "" + data.length);
            response.setContentType("application/octet-stream; charset=UTF-8");
            IOUtils.write(data, response.getOutputStream());
        } catch (IOException e){
            log.error("生成代码压缩错误:",e);
        }
    }
}
