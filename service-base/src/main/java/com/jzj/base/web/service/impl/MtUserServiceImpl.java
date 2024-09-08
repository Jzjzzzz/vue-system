package com.jzj.base.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.IMTCacheConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.web.mapper.MtUserMapper;
import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.pojo.entity.MtUser;
import com.jzj.base.web.service.MtItemService;
import com.jzj.base.web.service.MtLogService;
import com.jzj.base.web.service.MtShopService;
import com.jzj.base.web.service.MtUserService;
import com.jzj.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * <p>
 * i茅台用户Service业务层处理
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
@Service
@Slf4j
public class MtUserServiceImpl extends ServiceImpl<MtUserMapper, MtUser> implements MtUserService {

    private final static String SALT = "2af72f100c356273d46284f6fd1dfc08";

    private final static String AES_KEY = "qbhajinldepmucsonaaaccgypwuvcjaa";

    private final static String AES_IV = "2018534749963515";

    @Autowired
    private MtUserMapper mtUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MtShopService shopService;

    @Autowired
    private MtItemService itemService;

    @Autowired
    private MtLogService logService;

    /**
     * 获取验证码的md5签名，密钥+手机号+时间
     * 登录的md5签名：密钥+mobile+vCode+ydLogId+ydToken
     */
    private static String signature(String content, long time) {

        String text = SALT + content + time;
        String md5 = "";
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] hashBytes = md.digest(text.getBytes());
            StringBuilder sb = new StringBuilder();
            for (byte b : hashBytes) {
                sb.append(String.format("%02x", b));
            }
            md5 = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md5;
    }

    /**
     * 查询i茅台用户
     *
     * @param id i茅台用户主键
     * @return i茅台用户
     */
    @Override
    public MtUser selectMtUserById(String id) {
        return mtUserMapper.selectById(id);
    }

    /**
     * 查询i茅台用户列表
     *
     * @param mtUser i茅台用户
     * @return i茅台用户
     */
    @Override
    public List<MtUser> selectMtUserList(MtUser mtUser) {
        return mtUserMapper.selectMtUserList(mtUser);
    }

    /**
     * 新增i茅台用户
     *
     * @param mtUser i茅台用户
     * @return 结果
     */
    @Override
    public int insertMtUser(MtUser mtUser) {
        return mtUserMapper.insert(mtUser);
    }

    /**
     * 修改i茅台用户
     *
     * @param mtUser i茅台用户
     * @return 结果
     */
    @Override
    public int updateMtUser(MtUser mtUser) {
        MtShop mtShop = shopService.selectShopById(mtUser.getIshopId());
        if (mtShop == null) {
            throw new ServiceException("门店商品id不存在");
        }
        mtUser.setLng(mtShop.getLng());
        mtUser.setLat(mtShop.getLat());
        mtUser.setProvinceName(mtShop.getProvinceName());
        mtUser.setCityName(mtShop.getCityName());
        return mtUserMapper.updateById(mtUser);
    }

    /**
     * 批量删除i茅台用户
     *
     * @param ids 需要删除的i茅台用户主键
     * @return 结果
     */
    @Override
    public int deleteMtUserByIds(String[] ids) {
        return mtUserMapper.deleteBatchIds(Arrays.asList(ids));
    }

    /**
     * 发送验证码
     *
     * @param mobile   手机号
     * @param deviceId 设备id
     */
    @Override
    public Boolean sendCode(String mobile, String deviceId) {
        Map<String, Object> data = new HashMap<>();
        data.put("mobile", mobile);
        final long curTime = System.currentTimeMillis();
        data.put("md5", signature(mobile, curTime));
        data.put("timestamp", String.valueOf(curTime));
//        data.put("MT-APP-Version", MT_VERSION);

        HttpRequest request = HttpUtil.createRequest(Method.POST,
                "https://app.moutai519.com.cn/xhr/front/user/register/vcode");
        request.header("MT-Device-ID", deviceId);
        request.header("MT-APP-Version", getMTVersion());
        request.header("User-Agent", "iOS;16.3;Apple;?unrecognized?");

        request.header("Content-Type", "application/json");

        HttpResponse execute = request.body(JSONObject.toJSONString(data)).execute();
        JSONObject jsonObject = JSONObject.parseObject(execute.body());
        //成功返回 {"code":2000}
        log.info("「发送验证码返回」：" + jsonObject.toJSONString());
        if (jsonObject.getString("code").equals("2000")) {
            return Boolean.TRUE;
        } else {
            log.error("「发送验证码-失败」：" + jsonObject.toJSONString());
            throw new ServiceException("发送验证码错误");
        }
    }

    /**
     * 获取i茅台app版本号
     */
    @Override
    public String getMTVersion() {
        String mtVersion = Convert.toStr(redisCache.getCacheObject(IMTCacheConstants.MT_VERSION));
        if (StringUtils.isNotEmpty(mtVersion)) {
            return mtVersion;
        }
        String url = "https://apps.apple.com/cn/app/i%E8%8C%85%E5%8F%B0/id1600482450";
        String htmlContent = HttpUtil.get(url);
        Pattern pattern = Pattern.compile("new__latest__version\">(.*?)</p>", Pattern.DOTALL);
        Matcher matcher = pattern.matcher(htmlContent);
        if (matcher.find()) {
            mtVersion = matcher.group(1);
            mtVersion = mtVersion.replace("版本 ", "");
        }
        redisCache.setCacheObject(IMTCacheConstants.MT_VERSION, mtVersion);

        return mtVersion;
    }

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param code     code
     * @param deviceId 设备id
     */
    @Override
    public Boolean login(String mobile, String code, String deviceId) {
        Map<String, String> map = new HashMap<>();
        map.put("mobile", mobile);
        map.put("vCode", code);

        final long curTime = System.currentTimeMillis();
        map.put("md5", signature(mobile + code + "" + "", curTime));

        map.put("timestamp", String.valueOf(curTime));
        map.put("MT-APP-Version", getMTVersion());

        HttpRequest request = HttpUtil.createRequest(Method.POST,
                "https://app.moutai519.com.cn/xhr/front/user/register/login");
        MtUser user = mtUserMapper.selectById(mobile);
        if (user != null) {
            deviceId = user.getDeviceId();
        }
        request.header("MT-Device-ID", deviceId);
        request.header("MT-APP-Version", getMTVersion());
        request.header("User-Agent", "iOS;16.3;Apple;?unrecognized?");
        request.header("Content-Type", "application/json");

        HttpResponse execute = request.body(JSONObject.toJSONString(map)).execute();

        JSONObject body = JSONObject.parseObject(execute.body());

        if (body.getString("code").equals("2000")) {
            this.insertIUser(Long.parseLong(mobile), deviceId, body);
            return true;
        } else {
            log.error("「登录请求-失败」" + body.toJSONString());
            throw new ServiceException("登录失败，本地错误日志已记录");
        }
    }

    private void insertIUser(long mobile, String deviceId, JSONObject jsonObject) {
        MtUser user = mtUserMapper.selectById(mobile);
        if (user != null) {
            //存在则更新
            MtUser iUser = new MtUser(mobile, jsonObject);
            BeanUtil.copyProperties(iUser, user, "shopType", "minute");
            mtUserMapper.updateById(user);
        } else {
            if (StringUtils.isEmpty(deviceId)) {
                deviceId = UUID.randomUUID().toString().toLowerCase();
            }
            MtUser iUser = new MtUser(mobile, deviceId, jsonObject);
            mtUserMapper.insert(iUser);
        }
    }

    /**
     * 预约
     */
    @Override
    public void reservation(MtUser user) {
        if (StringUtils.isEmpty(user.getItemCode())) {
            return;
        }
        String[] items = user.getItemCode().split("@");

        String logContent = "";
        for (String itemId : items) {
            try {
                String shopId = shopService.getShopId(user.getShopType(), itemId,
                        user.getProvinceName(), user.getCityName(), user.getLat(), user.getLng());
                //预约
                JSONObject json = reservation(user, itemId, shopId);
                logContent += String.format("[预约项目]：%s\n[shopId]：%s\n[结果返回]：%s\n\n", itemId, shopId, json.toString());

                //随机延迟3～5秒
                Random random = new Random();
                int sleepTime = random.nextInt(3) + 3;
                Thread.sleep(sleepTime * 1000);
            } catch (Exception e) {
                logContent += String.format("执行报错--[预约项目]：%s\n[结果返回]：%s\n\n", itemId, e.getMessage());
            }
        }
        //日志记录
        logService.reservation(user, logContent);
        //预约后延迟领取耐力值
        getEnergyAwardDelay(user);
    }

    /**
     * 获取申购耐力值
     */
    @Override
    public String getEnergyAward(MtUser iUser) {
        String url = "https://h5.moutai519.com.cn/game/isolationPage/getUserEnergyAward";
        HttpRequest request = HttpUtil.createRequest(Method.POST, url);

        request.header("MT-Device-ID", iUser.getDeviceId())
                .header("MT-APP-Version", getMTVersion())
                .header("User-Agent", "iOS;16.3;Apple;?unrecognized?")
                .header("MT-Lat", iUser.getLat())
                .header("MT-Lng", iUser.getLng())
                .cookie("MT-Token-Wap=" + iUser.getCookie() + ";MT-Device-ID-Wap=" + iUser.getDeviceId() + ";");

        String body = request.execute().body();
        JSONObject jsonObject = JSONObject.parseObject(body);
        if (jsonObject.getInteger("code") != 200) {
            String message = jsonObject.getString("message");
            throw new ServiceException(message);
        }
        return body;
    }


    public JSONObject reservation(MtUser iUser, String itemId, String shopId) {
        Map<String, Object> map = new HashMap<>();
        JSONArray itemArray = new JSONArray();
        Map<String, Object> info = new HashMap<>();
        info.put("count", 1);
        info.put("itemId", itemId);

        itemArray.add(info);

        map.put("itemInfoList", itemArray);

        map.put("sessionId", itemService.getCurrentSessionId());
        map.put("userId", iUser.getUserId().toString());
        map.put("shopId", shopId);

        map.put("actParam", AesEncrypt(JSON.toJSONString(map)));

        HttpRequest request = HttpUtil.createRequest(Method.POST,
                "https://app.moutai519.com.cn/xhr/front/mall/reservation/add");

        request.header("MT-Lat", iUser.getLat());
        request.header("MT-Lng", iUser.getLng());
        request.header("MT-Token", iUser.getToken());
        request.header("MT-Info", "028e7f96f6369cafe1d105579c5b9377");
        request.header("MT-Device-ID", iUser.getDeviceId());
        request.header("MT-APP-Version", getMTVersion());
        request.header("User-Agent", "iOS;16.3;Apple;?unrecognized?");
        request.header("Content-Type", "application/json");
        request.header("userId", iUser.getUserId().toString());

        HttpResponse execute = request.body(JSONObject.toJSONString(map)).execute();

        JSONObject body = JSONObject.parseObject(execute.body());
        //{"code":2000,"data":{"successDesc":"申购完成，请于7月6日18:00查看预约申购结果","reservationList":[{"reservationId":17053404357,"sessionId":678,"shopId":"233331084001","reservationTime":1688608601720,"itemId":"10214","count":1}],"reservationDetail":{"desc":"申购成功后将以短信形式通知您，请您在申购成功次日18:00前确认支付方式，并在7天内完成提货。","lotteryTime":1688637600000,"cacheValidTime":1688637600000}}}
        if (body.getInteger("code") != 2000) {
            String message = body.getString("message");
            throw new ServiceException(message);
        }
//        logger.info(body.toJSONString());
        return body;
    }

    /**
     * 加密
     */
    public static String AesEncrypt(String params) {
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, AES_KEY.getBytes(), AES_IV.getBytes());
        return aes.encryptBase64(params);
    }

    /**
     * 延迟执行：获取申购耐力值，并记录日志
     */
    public void getEnergyAwardDelay(MtUser iUser) {
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                String logContent = "";
                //sleep 10秒
                try {
                    Thread.sleep(10000);
                    //预约后领取耐力值
                    String energyAward = getEnergyAward(iUser);
                    logContent += "[申购耐力值]:" + energyAward;
                } catch (Exception e) {
                    logContent += "执行报错--[申购耐力值]:" + e.getMessage();
                }
                //日志记录
                logService.reservation(iUser, logContent);
            }
        };
        new Thread(runnable).start();

    }
}
