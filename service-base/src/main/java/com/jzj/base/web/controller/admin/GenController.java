package com.jzj.base.web.controller.admin;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.jzj.common.annotation.Log;
import com.jzj.common.utils.result.R;
import com.jzj.common.utils.SqlUtil;
import com.jzj.common.utils.text.Convert;
import com.jzj.common.controller.BaseController;
import com.jzj.base.web.pojo.entity.GenTable;
import com.jzj.base.web.pojo.entity.GenTableColumn;
import com.jzj.common.pojo.enums.BusinessType;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.base.web.service.GenTableColumnService;
import com.jzj.base.web.service.GenTableService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成器 控制器
 * </p>
 *
 * @author Jzj
 * @since 2022/7/27 17:13
 */
@RestController
@RequestMapping("/tool/gen")
@Slf4j
public class GenController extends BaseController {

    @Autowired
    private GenTableService genTableService;

    @Autowired
    private GenTableColumnService genTableColumnService;

    @PreAuthorize("hasAuthority('btn.gen.list')")
    @ApiOperation("查询代码生成列表")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo genList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectGenTableList(genTable);
        return getDataTable(list);
    }

    @PreAuthorize("hasAuthority('btn.gen.list')")
    @ApiOperation("查询数据库列表")
    @GetMapping("/db/list")
    @ResponseBody
    public TableDataInfo dataList(GenTable genTable) {
        startPage();
        List<GenTable> list = genTableService.selectDbTableList(genTable);
        return getDataTable(list);
    }

    @PreAuthorize("hasAuthority('btn.gen.import')")
    @ApiOperation("导入表结构（保存）")
    @Log(title = "代码生成", businessType = BusinessType.IMPORT)
    @PostMapping("/importTable")
    public R importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList);
        return success();
    }

    @PreAuthorize("hasAuthority('btn.gen.del')")
    @ApiOperation("删除代码生成")
    @Log(title = "代码生成", businessType = BusinessType.DELETE)
    @DeleteMapping("/{tableIds}")
    public R remove(@PathVariable Long[] tableIds) {
        genTableService.deleteGenTableByIds(tableIds);
        return success();
    }

    @PreAuthorize("hasAuthority('btn.gen.gen')")
    @ApiOperation("生成代码（zip）")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/download/{tableName}")
    public void download(HttpServletResponse response, @PathVariable("tableName") String tableName) {
        byte[] data = genTableService.downloadCode(tableName);
        genCode(response, data);
    }

    @PreAuthorize("hasAuthority('btn.gen.gen')")
    @ApiOperation("生成代码（自定义路径）")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public R genCode(@PathVariable("tableName") String tableName) {
        genTableService.generatorCode(tableName);
        return success();
    }

    @PreAuthorize("hasAuthority('btn.gen.gen')")
    @ApiOperation("批量生成代码")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    public void batchGenCode(HttpServletResponse response, String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        byte[] data = genTableService.downloadCode(tableNames);
        genCode(response, data);
    }

    @PreAuthorize("hasAuthority('btn.gen.sql')")
    @ApiOperation("创建表结构（保存）")
    @Log(title = "创建表", businessType = BusinessType.OTHER)
    @PostMapping("/createTable")
    public R createTableSave(String sql) {
        try {
            SqlUtil.filterKeyword(sql);
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
            List<String> tableNames = new ArrayList<>();
            for (SQLStatement sqlStatement : sqlStatements) {
                if (sqlStatement instanceof MySqlCreateTableStatement) {
                    MySqlCreateTableStatement createTableStatement = (MySqlCreateTableStatement) sqlStatement;
                    if (genTableService.createTable(createTableStatement.toString())) {
                        String tableName = createTableStatement.getTableName().replaceAll("`", "");
                        tableNames.add(tableName);
                    }
                }
            }
            List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames.toArray(new String[tableNames.size()]));
            genTableService.importGenTable(tableList);
            return R.ok();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return R.error("创建表结构异常");
        }
    }

    @PreAuthorize("hasAuthority('btn.gen.list')")
    @ApiOperation("预览代码")
    @GetMapping("/preview/{tableId}")
    public R preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    @PreAuthorize("hasAuthority('btn.gen.edit')")
    @ApiOperation("修改代码生成业务")
    @GetMapping(value = "/{tableId}")
    public R getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return R.ok(map);
    }

    @PreAuthorize("hasAuthority('btn.gen.edit')")
    @ApiOperation("修改保存代码生成业务")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public R editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
        return success();
    }

    @PreAuthorize("hasAuthority('btn.gen.sync')")
    @ApiOperation("同步数据库")
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @GetMapping("/synchDb/{tableName}")
    public R syncDb(@PathVariable("tableName") String tableName) {
        genTableService.synchDb(tableName);
        return success();
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
        } catch (IOException e) {
            log.error("生成代码压缩错误:", e);
        }
    }
}
