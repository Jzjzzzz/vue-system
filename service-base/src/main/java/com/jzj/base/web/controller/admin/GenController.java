package com.jzj.base.web.controller.admin;

import com.alibaba.druid.DbType;
import com.alibaba.druid.sql.SQLUtils;
import com.alibaba.druid.sql.ast.SQLStatement;
import com.alibaba.druid.sql.dialect.mysql.ast.statement.MySqlCreateTableStatement;
import com.jzj.base.annotation.Log;
import com.jzj.base.utils.result.R;
import com.jzj.base.utils.sign.SqlUtil;
import com.jzj.base.utils.text.Convert;
import com.jzj.base.web.controller.BaseController;
import com.jzj.base.web.pojo.entity.GenTable;
import com.jzj.base.web.pojo.entity.GenTableColumn;
import com.jzj.base.web.pojo.enums.BusinessType;
import com.jzj.base.web.pojo.page.TableDataInfo;
import com.jzj.base.web.service.GenTableColumnService;
import com.jzj.base.web.service.GenTableService;
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

    @Autowired
    private GenTableColumnService genTableColumnService;



    /**
     * 查询代码生成列表
     */
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo genList(GenTable genTable) {
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
    public R importTableSave(String tables) {
        String[] tableNames = Convert.toStrArray(tables);
        // 查询表信息
        List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames);
        genTableService.importGenTable(tableList, getUsername());
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
     * 生成代码（自定义路径）
     */
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}")
    public R genCode(@PathVariable("tableName") String tableName)
    {
        genTableService.generatorCode(tableName);
        return success();
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
     * 创建表结构（保存）
     */
    @Log(title = "创建表", businessType = BusinessType.OTHER)
    @PostMapping("/createTable")
    public R createTableSave(String sql) {
        try
        {
            SqlUtil.filterKeyword(sql);
            List<SQLStatement> sqlStatements = SQLUtils.parseStatements(sql, DbType.mysql);
            List<String> tableNames = new ArrayList<>();
            for (SQLStatement sqlStatement : sqlStatements)
            {
                if (sqlStatement instanceof MySqlCreateTableStatement)
                {
                    MySqlCreateTableStatement createTableStatement = (MySqlCreateTableStatement) sqlStatement;
                    if (genTableService.createTable(createTableStatement.toString()))
                    {
                        String tableName = createTableStatement.getTableName().replaceAll("`", "");
                        tableNames.add(tableName);
                    }
                }
            }
            List<GenTable> tableList = genTableService.selectDbTableListByNames(tableNames.toArray(new String[tableNames.size()]));
            String operName = getUsername();
            genTableService.importGenTable(tableList, operName);
            return R.ok();
        }
        catch (Exception e)
        {
            log.error(e.getMessage(), e);
            return R.error("创建表结构异常");
        }
    }

    /**
     * 预览代码
     */
    @GetMapping("/preview/{tableId}")
    public R preview(@PathVariable("tableId") Long tableId) throws IOException {
        Map<String, String> dataMap = genTableService.previewCode(tableId);
        return R.ok(dataMap);
    }

    /**
     * 修改代码生成业务
     */
    @GetMapping(value = "/{tableId}")
    public R getInfo(@PathVariable Long tableId) {
        GenTable table = genTableService.selectGenTableById(tableId);
        List<GenTable> tables = genTableService.selectGenTableAll();
        List<GenTableColumn> list = genTableColumnService.selectGenTableColumnListByTableId(tableId);
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("info", table);
        map.put("rows", list);
        map.put("tables", tables);
        return R.ok(map);
    }

    /**
     * 修改保存代码生成业务
     */
    @Log(title = "代码生成", businessType = BusinessType.UPDATE)
    @PutMapping
    public R editSave(@Validated @RequestBody GenTable genTable) {
        genTableService.validateEdit(genTable);
        genTableService.updateGenTable(genTable);
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
        } catch (IOException e){
            log.error("生成代码压缩错误:",e);
        }
    }
}
