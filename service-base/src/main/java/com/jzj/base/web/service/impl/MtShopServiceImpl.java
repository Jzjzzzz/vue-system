package com.jzj.base.web.service.impl;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONException;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.IMTCacheConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.utils.sign.HttpUtils;
import com.jzj.base.web.mapper.MtShopMapper;
import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.pojo.vo.IMTItemInfo;
import com.jzj.base.web.pojo.vo.MapPoint;
import com.jzj.base.web.service.MtItemService;
import com.jzj.base.web.service.MtShopService;
import com.jzj.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 * i茅台商店Service业务层处理
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Service
public class MtShopServiceImpl extends ServiceImpl<MtShopMapper, MtShop> implements MtShopService {

    @Autowired
    private MtShopMapper mtShopMapper;

    @Autowired
    private MtShopService mtShopService;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MtItemService mtItemService;

    public static Double getDisdance(MapPoint point1, MapPoint point2) {
        double lat1 = (point1.getLatitude() * Math.PI) / 180; //将角度换算为弧度
        double lat2 = (point2.getLatitude() * Math.PI) / 180; //将角度换算为弧度
        double latDifference = lat1 - lat2;
        double lonDifference = (point1.getLongitude() * Math.PI) / 180 - (point2.getLongitude() * Math.PI) / 180;
        //计算两点之间距离   6378137.0 取自WGS84标准参考椭球中的地球长半径(单位:m)
        return 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(latDifference / 2), 2)
                + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(lonDifference / 2), 2))) * 6378137.0;
    }

    /**
     * 查询i茅台商店
     *
     * @param id i茅台商店主键
     * @return i茅台商店
     */
    @Override
    public MtShop selectMtShopById(String id) {
        return mtShopMapper.selectById(id);
    }

    /**
     * 查询i茅台商店列表
     *
     * @param mtShop i茅台商店
     * @return i茅台商店
     */
    @Override
    public List<MtShop> selectMtShopList(MtShop mtShop) {
        return mtShopMapper.selectMtShopList(mtShop);
    }

    /**
     * 新增i茅台商店
     */
    @Override
    public void insertMtShop() {
        String request = HttpUtils.sendGet(
                "https://static.moutai519.com.cn/mt-backend/xhr/front/mall/resource/get");
        JSONObject body = JSONObject.parseObject(request);
        //获取shop的url
        String shopUrl = body.getJSONObject("data").getJSONObject("mtshops_pc").getString("url");
        //清空数据库
        mtShopMapper.truncateShop();
        redisCache.deleteObject(IMTCacheConstants.MT_SHOP_LIST);
        String s = HttpUtils.sendGet(shopUrl);
        JSONObject jsonObject = JSONObject.parseObject(s);
        Set<String> shopIdSet = jsonObject.keySet();
        List<MtShop> list = new ArrayList<>();
        for (String iShopId : shopIdSet) {
            JSONObject shop = jsonObject.getJSONObject(iShopId);
            MtShop iShop = new MtShop(iShopId, shop);
            list.add(iShop);
        }
        mtShopService.saveBatch(list);
        redisCache.setCacheList(IMTCacheConstants.MT_SHOP_LIST, list);
        redisCache.expire(IMTCacheConstants.MT_SHOP_LIST, 2, TimeUnit.HOURS);
    }

    @Override
    public MtShop selectShopById(String shopId) {
        return mtShopMapper.selectOne(new QueryWrapper<MtShop>().eq("i_shop_id", shopId));
    }

    @Override
    public String getShopId(Long shopType, String itemId, String province, String city, String lat, String lng) {
        //查询所在省市的投放产品和数量
        List<IMTItemInfo> shopList = getShopsByProvince(province, itemId);
        //取id集合
        List<String> shopIdList = shopList.stream().map(IMTItemInfo::getShopId).collect(Collectors.toList());
        //获取门店列表
        List<MtShop> iShops = selectShopList();
        //获取今日的门店信息列表
        List<MtShop> list = iShops.stream().filter(i -> shopIdList.contains(i.getIShopId())).collect(Collectors.toList());

        String shopId = "";
        if (shopType == 1) {
            //预约本市出货量最大的门店
            shopId = getMaxInventoryShopId(shopList, list, city);
            if (StringUtils.isEmpty(shopId)) {
                //本市没有则预约本省最近的
                shopId = getMinDistanceShopId(list, province, lat, lng);
            }
        } else {
            //预约本省距离最近的门店
            shopId = getMinDistanceShopId(list, province, lat, lng);
        }

//        if (shopType == 2) {
//            // 预约本省距离最近的门店
//            shopId = getMinDistanceShopId(list, province, lat, lng);
//        }

        if (StringUtils.isEmpty(shopId)) {
            throw new ServiceException("申购时根据类型获取的门店商品id为空");
        }


        return shopId;
    }

    @Override
    public List<IMTItemInfo> getShopsByProvince(String province, String itemId) {
        String key = "mt_province:" + province + "." + mtItemService.getCurrentSessionId() + "." + itemId;
        List<IMTItemInfo> cacheList = redisCache.getCacheList(key);
        if (cacheList != null && cacheList.size() > 0) {
            return cacheList;
        } else {
            List<IMTItemInfo> imtItemInfoList = reGetShopsByProvince(province, itemId);
            redisCache.reSetCacheList(key, imtItemInfoList);
            redisCache.expire(key, 60, TimeUnit.MINUTES);
            return imtItemInfoList;
        }
    }

    public List<IMTItemInfo> reGetShopsByProvince(String province, String itemId) {

        long dayTime = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();

        String url = "https://static.moutai519.com.cn/mt-backend/xhr/front/mall/shop/list/slim/v3/" + mtItemService.getCurrentSessionId() + "/" + province + "/" + itemId + "/" + dayTime;

        String urlRes = HttpUtil.get(url);
        JSONObject res = null;
        try {
            res = JSONObject.parseObject(urlRes);
        } catch (JSONException jsonException) {
            String message = StringUtils.format("查询所在省市的投放产品和数量error: %s", url);
            log.error(message);
            throw new ServiceException(message);
        }

//        JSONObject res = JSONObject.parseObject(HttpUtil.get(url));
        if (!res.containsKey("code") || !res.getString("code").equals("2000")) {
            String message = StringUtils.format("查询所在省市的投放产品和数量error: %s", url);
            log.error(message);
            throw new ServiceException(message);
        }
        //组合信息
        List<IMTItemInfo> imtItemInfoList = new ArrayList<>();

        JSONObject data = res.getJSONObject("data");
        JSONArray shopList = data.getJSONArray("shops");

        for (Object obj : shopList) {
            JSONObject shops = (JSONObject) obj;
            JSONArray items = shops.getJSONArray("items");
            for (Object item : items) {
                JSONObject itemObj = (JSONObject) item;
                if (itemObj.getString("itemId").equals(itemId)) {
                    IMTItemInfo iItem = new IMTItemInfo(shops.getString("shopId"),
                            itemObj.getIntValue("count"), itemObj.getString("itemId"), itemObj.getIntValue("inventory"));
                    //添加
                    imtItemInfoList.add(iItem);
                }

            }


        }
        return imtItemInfoList;
    }

    @Override
    public List<MtShop> selectShopList() {
        List<MtShop> shopList = redisCache.getCacheList(IMTCacheConstants.MT_SHOP_LIST);
        if (shopList != null && shopList.size() > 0) {
            return shopList;
        } else {
            refreshShop();
        }
        shopList = mtShopMapper.selectList(null);
        return shopList;
    }

    @Override
    public void refreshShop() {
        HttpRequest request = HttpUtil.createRequest(Method.GET,
                "https://static.moutai519.com.cn/mt-backend/xhr/front/mall/resource/get");

        JSONObject body = JSONObject.parseObject(request.execute().body());
        //获取shop的url
        String shopUrl = body.getJSONObject("data").getJSONObject("mtshops_pc").getString("url");
        //清空数据库
        mtShopMapper.truncateShop();
        redisCache.deleteObject(IMTCacheConstants.MT_SHOP_LIST);

        String s = HttpUtil.get(shopUrl);

        JSONObject jsonObject = JSONObject.parseObject(s);
        Set<String> shopIdSet = jsonObject.keySet();
        List<MtShop> list = new ArrayList<>();
        for (String iShopId : shopIdSet) {
            JSONObject shop = jsonObject.getJSONObject(iShopId);
            MtShop iShop = new MtShop(iShopId, shop);
            list.add(iShop);
        }
        mtShopService.saveBatch(list);
        redisCache.setCacheList(IMTCacheConstants.MT_SHOP_LIST, list);
        redisCache.expire(IMTCacheConstants.MT_SHOP_LIST, 2, TimeUnit.HOURS);
    }

    /**
     * 预约本市出货量最大的门店
     */
    public String getMaxInventoryShopId(List<IMTItemInfo> list1, List<MtShop> list2, String city) {
        //本城市的shopId集合
        List<String> cityShopIdList = list2.stream().filter(iShop -> iShop.getCityName().contains(city))
                .map(MtShop::getIShopId).collect(Collectors.toList());

        List<IMTItemInfo> collect = list1.stream().filter(i -> cityShopIdList.contains(i.getShopId())).sorted(Comparator.comparing(IMTItemInfo::getInventory).reversed()).collect(Collectors.toList());
        if (collect.size() > 0) {
            return collect.get(0).getShopId();
        }
        return null;
    }

    /**
     * 预约本省距离最近的门店
     */
    public String getMinDistanceShopId(List<MtShop> list2, String province, String lat, String lng) {
        //本省的
        List<MtShop> iShopList = list2.stream().filter(iShop -> iShop.getProvinceName().contains(province))
                .collect(Collectors.toList());

        MapPoint myPoint = new MapPoint(Double.parseDouble(lat), Double.parseDouble(lng));
        for (MtShop iShop : iShopList) {
            MapPoint point = new MapPoint(Double.parseDouble(iShop.getLat()), Double.parseDouble(iShop.getLng()));
            Double disdance = getDisdance(myPoint, point);
            iShop.setDistance(disdance);
        }
        List<MtShop> collect = iShopList.stream().sorted(Comparator.comparing(MtShop::getDistance)).collect(Collectors.toList());

        return collect.get(0).getIShopId();
    }
}
