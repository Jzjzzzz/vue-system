package com.jzj.base.web.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jzj.base.utils.constant.CacheConstants;
import com.jzj.base.utils.redis.RedisCache;
import com.jzj.base.web.mapper.SysWebInformationMapper;
import com.jzj.base.web.pojo.entity.SysWebInformation;
import com.jzj.base.web.service.SysWebInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

/**
 * <p>
 * 网站基本信息 服务实现类
 * </p>
 *
 * @author Jzj
 * @since 2022-07-28
 */
@Service
public class SysWebInformationServiceImpl extends ServiceImpl<SysWebInformationMapper, SysWebInformation> implements SysWebInformationService {

    @Autowired
    private SysWebInformationMapper sysWebInformationMappereb;

    @Autowired
    private RedisCache redisCache;

    /**
     * 项目启动时，初始化参数到缓存
     */
    @PostConstruct
    public void init() {
        loadingWebInformationCache();
    }


    /**
     * 加载参数缓存数据
     */
    public void loadingWebInformationCache() {
        redisCache.setCacheObject(getCacheKey(), selectWebInformationById());
    }

    @Override
    public void clearInformationCache() {
        redisCache.deleteObject(CacheConstants.SYS_WEB_INFORMATION);
    }

    @Override
    public void resetInformationCache() {
        clearInformationCache();
        loadingWebInformationCache();
    }

    @Override
    public SysWebInformation selectWebInformationById() {
        SysWebInformation information;
        information = redisCache.getCacheObject(CacheConstants.SYS_WEB_INFORMATION);
        if (information == null) {
            information = sysWebInformationMappereb.selectById(1);
            if (information == null) {
                return new SysWebInformation();
            }
        }
        return information;
    }

    @Override
    public int updateWebInformation(SysWebInformation sysWebInformation) {
        int result = sysWebInformationMappereb.updateById(sysWebInformation);
        //刷新缓存
        resetInformationCache();
        return result;
    }

    /**
     * 设置cache keY
     *
     * @return 缓存键key
     */
    private String getCacheKey() {
        return CacheConstants.SYS_WEB_INFORMATION;
    }
}
