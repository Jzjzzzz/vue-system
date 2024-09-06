package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.MtItem;

import java.util.List;

/**
 * <p>
 * i茅台预约商品Service接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtItemService extends IService<MtItem> {

    /**
     * 查询I茅台预约商品
     *
     * @param id I茅台预约商品主键
     * @return I茅台预约商品
     */
    MtItem selectMtItemById(String id);

    /**
     * 查询I茅台预约商品列表
     *
     * @param mtItem I茅台预约商品
     * @return I茅台预约商品集合
     */
    List<MtItem> selectMtItemList(MtItem mtItem);

    /**
     * 新增I茅台预约商品
     */
    void insertMtItem();

    /**
     * 获取当天的sessionId
     */
    String getCurrentSessionId();

    /**
     * 查询所有商品
     */
    List<MtItem> selectList();
}
