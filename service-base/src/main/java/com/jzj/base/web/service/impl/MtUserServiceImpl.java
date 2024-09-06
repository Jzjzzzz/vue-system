package com.jzj.base.web.service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.http.Method;
import com.alibaba.fastjson.JSONObject;
import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.IMTCacheConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.web.mapper.MtUserMapper;
import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.pojo.entity.MtUser;
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

    @Autowired
    private MtUserMapper mtUserMapper;

    @Autowired
    private RedisCache redisCache;

    @Autowired
    private MtShopService shopService;

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
     * 删除i茅台用户信息
     *
     * @param id i茅台用户主键
     * @return 结果
     */
    @Override
    public int deleteMtUserById(String id) {
        return mtUserMapper.deleteById(id);
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
//        if (StringUtils.isEmpty(user.getItemCode())) {
//            return;
//        }
//        String[] items = user.getItemCode().split("@");
//
//        String logContent = "";
//        for (String itemId : items) {
//            try {
//                String shopId = shopService.getShopId(user.getShopType(), itemId,
//                        user.getProvinceName(), user.getCityName(), user.getLat(), user.getLng());
//                //预约
//                JSONObject json = reservation(user, itemId, shopId);
//                logContent += String.format("[预约项目]：%s\n[shopId]：%s\n[结果返回]：%s\n\n", itemId, shopId, json.toString());
//
//                //随机延迟3～5秒
//                Random random = new Random();
//                int sleepTime = random.nextInt(3) + 3;
//                Thread.sleep(sleepTime * 1000);
//            } catch (Exception e) {
//                logContent += String.format("执行报错--[预约项目]：%s\n[结果返回]：%s\n\n", itemId, e.getMessage());
//            }
//        }
    }
}
