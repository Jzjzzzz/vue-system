package com.jzj.base.web.service.impl;

import com.aliyun.oss.ServiceException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.web.mapper.MtUserMapper;
import com.jzj.base.web.pojo.entity.MtShop;
import com.jzj.base.web.pojo.entity.MtUser;
import com.jzj.base.web.service.MtShopService;
import com.jzj.base.web.service.MtUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

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

    @Autowired
    private MtUserMapper mtUserMapper;

    @Autowired
    private MtUserService mtUserService;

    @Autowired
    private MtShopService shopService;

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
     * 通过预约执行分钟查询预约用户列表
     */
    @Override
    public List<MtUser> selectReservationUserByMinute(int minute) {
        return mtUserMapper.selectReservationUser(minute);
    }

    /**
     * 预购时间每日重置(9-10 0-59分)
     */
    @Override
    public void resetTimeDaily() {
        List<MtUser> list = mtUserMapper.selectList(new QueryWrapper<MtUser>().eq("random_minute", '0'));
        if(list.size()>0){
            Random random = new Random();
            list.forEach(s-> s.setMinute((long) random.nextInt(60)));
            mtUserService.updateBatchById(list);
        }
    }

    @Override
    public List<MtUser> selectReservationUser() {
        return mtUserMapper.selectReservationUser(-1);
    }

}
