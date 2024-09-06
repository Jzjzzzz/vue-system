package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.MtItem;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * i茅台预约商品Mapper接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtItemMapper extends BaseMapper<MtItem> {

    /**
     * 查询I茅台预约商品列表
     *
     * @param mtItem I茅台预约商品
     * @return I茅台预约商品集合
     */
    List<MtItem> selectMtItemList(MtItem mtItem);

    /**
     * 清空指定表
     */
    @Update("truncate table mt_item")
    void truncateItem();

}
