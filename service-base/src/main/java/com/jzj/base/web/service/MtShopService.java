package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.pojo.vo.IMTItemInfo;

import java.util.List;

/**
 * <p>
 * i茅台商店Service接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtShopService extends IService<MtShop> {

    /**
     * 查询i茅台商店
     *
     * @param id i茅台商店主键
     * @return i茅台商店
     */
    MtShop selectMtShopById(String id);

    /**
     * 查询i茅台商店列表
     *
     * @param mtShop i茅台商店
     * @return i茅台商店集合
     */
    List<MtShop> selectMtShopList(MtShop mtShop);

    /**
     * 新增i茅台商店
     */
    void insertMtShop();

    MtShop selectShopById(String ishopId);

    /**
     * @param shopType 1：预约本市出货量最大的门店，2：预约你的位置附近门店
     * @param itemId   项目id即预约项目code
     * @param province 省份，例如：河北省，北京市
     * @param city     市：例如石家庄市
     */
    String getShopId(Long shopType, String itemId, String province, String city, String lat, String lng);

    /**
     * 查询所在省市的投放产品和数量
     *
     * @param province 省份，例如：河北省，北京市
     * @param itemId   项目id即预约项目code
     */
    List<IMTItemInfo> getShopsByProvince(String province, String itemId);

    List<MtShop> selectShopList();

    /**
     * 刷新数据库i茅台shop列表
     */
    void refreshShop();
}
