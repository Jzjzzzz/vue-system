package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.MtShop;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * <p>
 * i茅台商店Mapper接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtShopMapper extends BaseMapper<MtShop> {
    /**
     * 查询i茅台商店列表
     *
     * @param mtShop i茅台商店
     * @return i茅台商店集合
     */
    List<MtShop> selectMtShopList(MtShop mtShop);

    /**
     * 清空指定表
     */
    @Update("truncate table mt_shop")
    void truncateShop();
}
