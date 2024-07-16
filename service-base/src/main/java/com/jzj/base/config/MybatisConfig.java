package com.jzj.base.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author Jzj
 * @Date 2022/6/6 16:43
 * @Version 1.0
 * @Message: Mybatis配置类
 */
@Configuration
@MapperScan("com.jzj.base.web.mapper")
public class MybatisConfig {
    /**
     * 插件配置
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 分页插件(如果配置多个插件, 切记分页最后添加)
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }
}
