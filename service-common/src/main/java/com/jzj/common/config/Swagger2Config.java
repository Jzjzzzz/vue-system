package com.jzj.common.config;

import com.google.common.base.Predicates;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * <p>
 * Swagger配置类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Configuration
@EnableSwagger2
@ConditionalOnProperty(name = "swagger.enable",havingValue = "true")
public class Swagger2Config {

    @Bean
    public Docket adminApiConfig() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("adminApi")
                .apiInfo(adminApiInfo())
                .select()
                .paths(Predicates.not(PathSelectors.regex("/error.*")))
                .build();
    }

    private ApiInfo adminApiInfo() {
        return new ApiInfoBuilder()
                .title("后台管理系统API文档")
                .description("本文档描述了后台管理系统的各个模块的接口的调用方式")
                .version("1.0")
                .contact(new Contact("漫漫长路", "Jzjzzzz.icu", "946232976@qq.com"))
                .build();
    }
}
