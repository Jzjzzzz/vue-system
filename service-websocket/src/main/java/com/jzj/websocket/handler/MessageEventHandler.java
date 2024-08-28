package com.jzj.websocket.handler;

import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.jzj.websocket.config.Connect;
import com.jzj.websocket.constant.Event;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * <p>
 * 消息事件处理
 * </p>
 *
 * @author Jzj
 * @since 2024/8/27 16:45
 */
@Component
@Slf4j
public class MessageEventHandler {

    @Autowired
    private SocketIOServer socketServer;

    /**
     * 链接事件
     * @param client 客户端
     */
    @OnConnect
    public void onConnect(SocketIOClient client){
        if(client!=null){
            String token = client.getHandshakeData().getSingleUrlParam("token");
            UUID sessionId = client.getSessionId();
            Connect.save(token,sessionId);
        }
    }

    /**
     * 广播
     * @param message 消息
     */
    public void broadcast(String message){
        for (UUID uuid : Connect.list()) {
            if(socketServer.getClient(uuid)==null) continue;
            socketServer.getClient(uuid).sendEvent(Event.BROADCAST,message);
        }
    }
}
