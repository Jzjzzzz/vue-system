package com.jzj.base.handler.rabbit;

import com.jzj.base.utils.constant.RabbitConstants;
import com.jzj.base.web.pojo.entity.rabbit.EmailMessageStruct;
import com.jzj.base.web.service.EmailService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p>
 * 发送Email队列处理器
 * </p>
 *
 * @author Jzj
 * @since  2022/7/22 11:12
 */
@Component
public class EmailQueueHandler {

    @Autowired
    private EmailService emailService;

    @RabbitListener(queues = RabbitConstants.EMAIL_QUEUE)
    public void ListenerQueue(EmailMessageStruct message){
        emailService.sendMail(message.getEmail(),message.getNickName()+"对您留言的回复-"+"来着漫漫长路的博客",message.getReply());
    }
}
