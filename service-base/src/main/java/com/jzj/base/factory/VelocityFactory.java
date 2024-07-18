package com.jzj.base.factory;

import com.jzj.base.utils.constant.Constants;
import org.apache.velocity.app.Velocity;

import java.util.Properties;

/**
 * @Author Jzj
 * @Date 2024/7/17 上午11:35
 * @Version 1.0
 * @Message: VelocityEngine工厂
 */
public class VelocityFactory {
    /**
     * 初始化vm方法
     */
    public static void initVelocity() {
        Properties p = new Properties();
        try {
            // 加载classpath目录下的vm文件
            p.setProperty("resource.loader.file.class", "org.apache.velocity.runtime.resource.loader.ClasspathResourceLoader");
            // 定义字符集
            p.setProperty(Velocity.INPUT_ENCODING, Constants.UTF8);
            // 初始化Velocity引擎，指定配置Properties
            Velocity.init(p);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
