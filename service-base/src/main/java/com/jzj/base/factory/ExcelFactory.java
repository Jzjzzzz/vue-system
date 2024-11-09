package com.jzj.base.factory;

import com.jzj.base.listener.base.BaseExcelListener;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author Jzj
 * @since 2024/11/9 02:16
 */
@Component
public class ExcelFactory implements ApplicationContextAware {

    /**
     * 储存自定义监视器的容器
     */
    private static Map<String, BaseExcelListener> map;

    /**
     * 获取指定的自定义监视器
     * @param str code
     * @return 监视器
     */
    public static BaseExcelListener getInvokeStrategy(String str){
        return map.get(str);
    }


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, BaseExcelListener> beans = applicationContext.getBeansOfType(BaseExcelListener.class);
        map = new ConcurrentHashMap<>();
        beans.forEach((k, v) -> map.put(v.getCode(), v));
    }
}
