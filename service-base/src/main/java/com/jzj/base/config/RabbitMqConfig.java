package com.jzj.base.config;

import com.jzj.base.utils.constant.RabbitConstants;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p>
 * RabbitMQ配置类
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Configuration
public class RabbitMqConfig {

    @Bean("bootQueue")
    public Queue bootQueue(){
        return QueueBuilder.durable(RabbitConstants.EMAIL_QUEUE).build();
    }
}
