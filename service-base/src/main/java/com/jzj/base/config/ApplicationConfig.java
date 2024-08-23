package com.jzj.base.config;

import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.util.TimeZone;

/**
 * <p>
 * 程序注解配置
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Configuration
@EnableAspectJAutoProxy(exposeProxy = true) //表示通过aop框架暴露该代理对象,AopContext能够访问
@ComponentScan({"com.jzj.*"})
public class ApplicationConfig {
    /**
     * 时区配置
     */
    @Bean
    public Jackson2ObjectMapperBuilderCustomizer jacksonObjectMapperCustomization() {
        return jacksonObjectMapperBuilder -> jacksonObjectMapperBuilder.timeZone(TimeZone.getDefault());
    }
}
