package com.jzj.base.web.service;

import com.jzj.base.web.pojo.entity.GenTable;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 代码生成 服务层
 * </p>
 *
 * @author Jzj
 * @since 2024-04-30
 */
public interface GenTableService {

    /**
     * 查询业务列表
     *
     * @param genTable 业务信息
     * @return 业务集合
     */
    List<GenTable> selectGenTableList(GenTable genTable);

    /**
     * 查询据库列表
     *
     * @param genTable 业务信息
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableList(GenTable genTable);

    /**
     * 根据名称查询数据库列表
     *
     * @param tableNames 表名称组
     * @return 数据库表集合
     */
    List<GenTable> selectDbTableListByNames(String[] tableNames);

    /**
     * 导入表结构
     *
     * @param tableList 导入表列表
     * @param name 操作人员
     */
    void importGenTable(List<GenTable> tableList, String name);

    /**
     * 删除业务信息
     *
     * @param tableIds 需要删除的表数据ID
     * @return 结果
     */
    void deleteGenTableByIds(Long[] tableIds);

    /**
     * 生成代码（下载方式）
     *
     * @param tableName 表名称
     * @return 数据
     */
    byte[] downloadCode(String tableName);

    /**
     * 批量生成代码（下载方式）
     *
     * @param tableNames 表数组
     * @return 数据
     */
    byte[] downloadCode(String[] tableNames);

    /**
     * 创建表
     *
     * @param sql 创建表语句
     * @return 结果
     */
    boolean createTable(String sql);

    /**
     * 预览代码
     *
     * @param tableId 表编号
     * @return 预览数据列表
     */
    Map<String, String> previewCode(Long tableId);

    /**
     * 查询业务信息
     *
     * @param id 业务ID
     * @return 业务信息
     */
    GenTable selectGenTableById(Long id);

    /**
     * 查询所有表信息
     *
     * @return 表信息集合
     */
    List<GenTable> selectGenTableAll();

    /**
     * 修改保存参数校验
     *
     * @param genTable 业务信息
     */
    void validateEdit(GenTable genTable);

    /**
     * 修改业务
     *
     * @param genTable 业务信息
     * @return 结果
     */
    void updateGenTable(GenTable genTable);

    /**
     * 生成代码（自定义路径）
     *
     * @param tableName 表名称
     * @return 数据
     */
    void generatorCode(String tableName);

    /**
     * 同步数据库
     *
     * @param tableName 表名称
     */
    void synchDb(String tableName);
}
