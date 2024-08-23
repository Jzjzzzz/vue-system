package com.jzj.base.factory;

import com.jzj.base.utils.constant.CacheConstants;
import com.jzj.base.web.pojo.enums.UploadCode;
import com.jzj.base.web.service.SysConfigService;
import com.jzj.base.web.service.UploadService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 文件上传工厂类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Component
public class UploadFactory implements ApplicationContextAware {


    private static final String OPEN = "true";
    private static Map<UploadCode, UploadService> uploadServiceMap;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, UploadService> map = applicationContext.getBeansOfType(UploadService.class);
        uploadServiceMap = new HashMap<>();
        map.forEach((k, v) -> uploadServiceMap.put(v.getCode(), v));
    }

    public static UploadService getUploadService(SysConfigService sysConfigService) {
        //获取阿里云oss是否开启
        String enable = sysConfigService.selectConfigByKey(CacheConstants.A_LI_YUN_ENABLE_CODE);
        if (OPEN.equals(enable)) {
            //阿里云存储
            return uploadServiceMap.get(UploadCode.A_LI_YUN);
        } else {
            //本地存储
            return uploadServiceMap.get(UploadCode.LOCAL);
        }
    }
}
