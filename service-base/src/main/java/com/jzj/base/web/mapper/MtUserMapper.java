package com.jzj.base.web.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jzj.base.web.pojo.entity.MtUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * i茅台用户Mapper接口
 * </p>
 *
 * @author Jzj
 * @since 2024-09-06
 */
public interface MtUserMapper extends BaseMapper<MtUser> {
    /**
     * 查询i茅台用户列表
     *
     * @param mtUser i茅台用户
     * @return i茅台用户集合
     */
    List<MtUser> selectMtUserList(MtUser mtUser);

    /**
     * 查询预约用户列表
     */
    List<MtUser> selectReservationUser(@Param("minute")int minute);
}
