package com.jzj.websocket.handler;

import com.corundumstudio.socketio.AckRequest;
import com.corundumstudio.socketio.SocketIOClient;
import com.corundumstudio.socketio.SocketIOServer;
import com.corundumstudio.socketio.annotation.OnConnect;
import com.corundumstudio.socketio.annotation.OnEvent;
import com.jzj.common.utils.result.R;
import com.jzj.websocket.config.Connect;
import com.jzj.websocket.constant.Event;
import com.jzj.websocket.pojo.SingleMessageRequest;
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
        for (UUID uuid : Connect.values()) {
            if(socketServer.getClient(uuid)==null) continue;
            socketServer.getClient(uuid).sendEvent(Event.BROADCAST,message);
        }
    }

    /**
     * 指定用户广播
     * @param message 消息
     * @param id 用户id
     */
    public <T> void send(String message,Object id){
        if(Connect.isContains(id)){
            UUID uuid = Connect.getById(id);
            socketServer.getClient(uuid).sendEvent(Event.BROADCAST,message);
        }
    }

    @OnEvent(value = Event.CHAT)
    public void onChat(SocketIOClient client, AckRequest request, SingleMessageRequest messageRequest){
        String toUid = messageRequest.getToUid();
        if(Connect.isContains(toUid)){
            UUID uuid = Connect.getById(toUid);
            socketServer.getClient(uuid).sendEvent(Event.CHAT,messageRequest.getMessage());
            request.sendAckData(R.ok("发送成功!"));
        } else {
            request.sendAckData(R.error("发送失败，对方并不在线!"));
        }
    }

    /**
     * 登录初始化
     * @param client 客户端
     * @param token token
     */
    @OnEvent(value = Event.LOGIN)
    public void onLogin(SocketIOClient client,String token){
        if(client!=null){
            UUID sessionId = client.getSessionId();
            Connect.save(token,sessionId);
        }
    }
}
