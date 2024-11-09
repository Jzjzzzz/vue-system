package com.jzj.base.listener.base;

import com.alibaba.excel.read.listener.ReadListener;

import java.io.InputStream;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <p>
 * EasyExcel 上传通用接口
 * </p>
 *
 * @author Jzj
 * @since 2024/9/1 01:54
 */
public abstract class BaseExcelListener<T> implements ReadListener<T> {

    /**
     * 单次缓存的数据量
     */
    public static final int BATCH_COUNT = 100;

    /**
     *临时存储
     */
    public List<T> cachedDataList = new CopyOnWriteArrayList<>();

    /**
     * 执行read操作
     * @param inputStream 数据流
     */
    public abstract void read(InputStream inputStream);


    /**
     * 执行入库操作
     */
    public abstract void saveData();

    /**
     * 监听器唯一值
     */
    public abstract String getCode();

}
