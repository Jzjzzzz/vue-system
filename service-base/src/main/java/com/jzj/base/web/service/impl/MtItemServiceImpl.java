package com.jzj.base.web.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.IMTCacheConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.utils.sign.HttpUtils;
import com.jzj.base.web.mapper.MtItemMapper;
import com.jzj.base.web.pojo.entity.MtItem;
import com.jzj.base.web.service.MtItemService;
import com.jzj.common.utils.StringUtils;
import com.jzj.common.utils.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * <p>
 * i茅台预约商品Service业务层处理
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Service
public class MtItemServiceImpl extends ServiceImpl<MtItemMapper, MtItem> implements MtItemService {

    @Autowired
    private MtItemMapper mtItemMapper;

    @Autowired
    private RedisCache redisCache;

    /**
     * 查询i茅台预约商品
     *
     * @param id i茅台预约商品主键
     * @return i茅台预约商品
     */
    @Override
    public MtItem selectMtItemById(String id) {
        return mtItemMapper.selectById(id);
    }

    /**
     * 查询i茅台预约商品列表
     *
     * @param mtItem i茅台预约商品
     * @return i茅台预约商品
     */
    @Override
    public List<MtItem> selectMtItemList(MtItem mtItem) {
        return mtItemMapper.selectMtItemList(mtItem);
    }

    /**
     * 新增i茅台预约商品
     */
    @Override
    public void insertMtItem() {
        redisCache.deleteObject(IMTCacheConstants.MT_SESSION_ID);
        getCurrentSessionId();
    }

    @Override
    public String getCurrentSessionId() {
        String mtSessionId = Convert.toStr(redisCache.getCacheObject(IMTCacheConstants.MT_SESSION_ID));

        long dayTime = LocalDate.now().atStartOfDay().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        if (StringUtils.isNotEmpty(mtSessionId)) {
            return mtSessionId;
        }

        String res = HttpUtils.sendGet("https://static.moutai519.com.cn/mt-backend/xhr/front/mall/index/session/get/" + dayTime);
        //替换 current_session_id 673 ['data']['sessionId']
        JSONObject jsonObject = JSONObject.parseObject(res);

        if (jsonObject.getString("code").equals("2000")) {
            JSONObject data = jsonObject.getJSONObject("data");
            mtSessionId = data.getString("sessionId");
            redisCache.setCacheObject(IMTCacheConstants.MT_SESSION_ID, mtSessionId, 2, TimeUnit.HOURS);

            mtItemMapper.truncateItem();
            //item插入数据库
            JSONArray itemList = data.getJSONArray("itemList");
            for (Object obj : itemList) {
                JSONObject item = (JSONObject) obj;
                MtItem iItem = new MtItem(item);
                mtItemMapper.insert(iItem);
            }

        }
        return mtSessionId;
    }

    /**
     * 查询所有商品
     */
    @Override
    public List<MtItem> selectList() {
        return mtItemMapper.selectList(null);
    }
}
