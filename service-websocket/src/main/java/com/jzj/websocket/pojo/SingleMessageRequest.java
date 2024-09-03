package com.jzj.websocket.pojo;

import lombok.Data;

/**
 * <p>
 * 私聊消息载荷
 * </p>
 *
 * @author Jzj
 * @since 2024/9/2 15:13
 */
@Data
public class SingleMessageRequest {

    /**
     * 消息发送方用户id
     */
    private String fromUid;

    /**
     * 消息接收方用户id
     */
    private String toUid;

    /**
     * 消息内容
     */
    private String message;
}
