package com.jzj.base.utils.properties;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 代码生成相关配置
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Component
@Data
@ConfigurationProperties(prefix = "gen")
public class GenProperties implements InitializingBean {
    /**
     * 作者
     */
    private String author;

    /**
     * 生成包路径
     */
    private String packageName;

    /**
     * 自动去除表前缀，默认是false
     */
    private boolean autoRemovePre;

    /**
     * 表前缀(类名不会包含表前缀)
     */
    private String tablePrefix;

    /**
     * 持久层框架(0-mybatis,1-mybatis-plus)
     */
    private String ormType;

    public static String AUTHOR;

    public static String PACKAGE_NAME;

    public static boolean AUTO_REMOVE_PRE;

    public static String TABLE_PRE_FIX;

    public static String ORM_TYPE;


    @Override
    public void afterPropertiesSet() throws Exception {
        AUTHOR = author;
        PACKAGE_NAME = packageName;
        AUTO_REMOVE_PRE = autoRemovePre;
        TABLE_PRE_FIX = tablePrefix;
        ORM_TYPE = ormType;
    }
}
