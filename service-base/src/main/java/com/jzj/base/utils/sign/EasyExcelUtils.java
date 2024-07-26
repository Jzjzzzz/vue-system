package com.jzj.base.utils.sign;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.style.column.LongestMatchColumnWidthStyleStrategy;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.List;

/**
 * @Author Jzj
 * @Date 2024/7/26 下午2:35
 * @Version 1.0
 * @Message: EasyExcel工具类
 */
@Slf4j
public class EasyExcelUtils {

    /**
     * 导出Excel
     * @param name 文件名
     * @param list 数据集
     * @param clazz 模板类
     */
    public static <T> void export(String name, List<T> list, Class<T> clazz, HttpServletResponse response){
        try {
            response.setContentType("application/vnd.ms-excel");
            response.setCharacterEncoding("utf-8");
            String fileName = URLEncoder.encode(name, "UTF-8").replaceAll("\\+", "%20");
            response.setHeader("Content-disposition", "attachment;filename*=utf-8''" + fileName + ".xlsx");
            EasyExcel.write(response.getOutputStream(), clazz)
                    .autoCloseStream(true)
                    .registerWriteHandler(new LongestMatchColumnWidthStyleStrategy())
                    .sheet(name)
                    .doWrite(list);
        } catch (IOException e) {
            log.error("导出Excel失败！", e);
        }
    }
}
