package com.jzj.base.web.service;

import com.jzj.base.web.pojo.entity.MtUser;

/**
 * <p>
 * i茅台API调用Service接口
 * </p>
 *
 * @author Jzj
 * @since 2024/9/10 14:32
 */

public interface MtApiService {

    /**
     * 获取i茅台app版本号
     */
    String getMTVersion();

    /**
     * 每日预约申购结果
     */
    void appointmentResults();

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param code     code
     * @param deviceId 设备id
     */
    Boolean login(String mobile, String code, String deviceId);

    /**
     * 发送验证码
     *
     * @param mobile   手机号
     * @param deviceId 设备id
     */
    Boolean sendCode(String mobile, String deviceId);

    /**
     * 预约
     * @param user i茅台用户实体
     */
    void reservation(MtUser user);

    /**
     * 获取申购耐力值
     */
    String getEnergyAward(MtUser iUser);

    /**
     * 获得旅行奖励
     */
    void getTravelReward(MtUser user);

    /**
     * 批量预约
     */
    void reservationBatch();

    /**
     * 批量获取旅游奖励
     */
    void getTravelRewardBatch();
}
