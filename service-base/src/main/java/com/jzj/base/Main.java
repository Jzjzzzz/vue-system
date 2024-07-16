package com.jzj.base;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.retry.annotation.EnableRetry;

/**
 * @Author Jzj
 * @Date 2024/7/5 上午11:20
 * @Version 1.0
 * @Message:
 */
@SpringBootApplication
@EnableRetry
@Slf4j
public class Main {

    public static void main(String[] args) {
        try {
            ApplicationContext run = SpringApplication.run(Main.class, args);
            Environment env = run.getEnvironment();
            log.info("启动成功!");
            log.info("地址:\thttp://127.0.0.1:{}", env.getProperty("server.port"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}