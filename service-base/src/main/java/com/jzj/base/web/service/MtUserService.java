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
     * 通过预约执行分钟查询预约用户列表
     */
    List<MtUser> selectReservationUserByMinute(int minute);

    /**
     * 预购时间每日重置(9-10 0-59分)
     */
    void resetTimeDaily();

    /**
     * 查询预约用户列表
     */
    List<MtUser> selectReservationUser();
}
