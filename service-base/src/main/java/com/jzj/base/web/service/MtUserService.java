package com.jzj.base.web.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jzj.base.web.pojo.entity.MtUser;

import java.util.List;

/**
 * <p>
 * i茅台用户Service接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtUserService extends IService<MtUser> {

    /**
     * 查询i茅台用户
     *
     * @param id i茅台用户主键
     * @return i茅台用户
     */
    MtUser selectMtUserById(String id);

    /**
     * 查询i茅台用户列表
     *
     * @param mtUser i茅台用户
     * @return i茅台用户集合
     */
    List<MtUser> selectMtUserList(MtUser mtUser);

    /**
     * 新增i茅台用户
     *
     * @param mtUser i茅台用户
     * @return 结果
     */
    int insertMtUser(MtUser mtUser);

    /**
     * 修改i茅台用户
     *
     * @param mtUser i茅台用户
     * @return 结果
     */
    int updateMtUser(MtUser mtUser);

    /**
     * 批量删除i茅台用户
     *
     * @param ids 需要删除的i茅台用户主键集合
     * @return 结果
     */
    int deleteMtUserByIds(String[] ids);

    /**
     * 发送验证码
     *
     * @param mobile   手机号
     * @param deviceId 设备id
     */
    Boolean sendCode(String mobile, String deviceId);

    /**
     * 获取i茅台app版本号
     */
    String getMTVersion();

    /**
     * 登录
     *
     * @param mobile   手机号
     * @param code     code
     * @param deviceId 设备id
     */
    Boolean login(String mobile, String code, String deviceId);

    /**
     * 预约
     */
    void reservation(MtUser user);

    /**
     * 获取申购耐力值
     */
    String getEnergyAward(MtUser iUser);
}
