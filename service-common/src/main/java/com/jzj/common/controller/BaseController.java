package com.jzj.common.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.jzj.common.pojo.page.PageDomain;
import com.jzj.common.pojo.page.TableDataInfo;
import com.jzj.common.pojo.page.TableSupport;
import com.jzj.common.utils.DateUtils;
import com.jzj.common.utils.PageUtils;
import com.jzj.common.utils.SqlUtil;
import com.jzj.common.utils.result.R;
import com.jzj.common.utils.result.ResultCode;
import org.apache.commons.lang3.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * web层通用数据处理
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
public class BaseController {


    /**
     * 将前台传递过来的日期格式的字符串，自动转化为Date类型
     */
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        // Date 类型转换
        binder.registerCustomEditor(Date.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                setValue(DateUtils.parseDate(text));
            }
        });
    }

    /**
     * 设置请求分页数据
     */
    protected void startPage() {
        PageUtils.startPage();
    }

    /**
     * 设置请求排序数据
     */
    protected void startOrderBy() {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        if (StringUtils.isNotEmpty(pageDomain.getOrderBy())) {
            String orderBy = SqlUtil.escapeOrderBySql(pageDomain.getOrderBy());
            PageHelper.orderBy(orderBy);
        }
    }

    /**
     * 响应请求分页数据
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    protected TableDataInfo getDataTable(List<?> list) {
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(ResultCode.SUCCESS);
        rspData.setMsg("查询成功");
        rspData.setRows(list);
        rspData.setTotal(new PageInfo(list).getTotal());
        return rspData;
    }

    /**
     * 清理分页的线程变量
     */
    protected void clearPage() {
        PageUtils.clearPage();
    }

    /**
     * 响应返回结果
     *
     * @param rows 影响行数
     * @return 操作结果
     */
    protected R toAjax(int rows) {
        return rows > 0 ? R.ok() : R.error();
    }

    /**
     *  响应返回结果
     * @param result 执行结果
     * @return 操作结果
     */
    protected R toAjax(boolean result){
        return result? R.ok() : R.error();
    }

    /**
     * 返回成功
     */
    public R success() {
        return R.ok();
    }

    /**
     * 返回失败消息
     */
    public R error() {
        return R.error();
    }
}
