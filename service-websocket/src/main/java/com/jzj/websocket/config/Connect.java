package com.jzj.websocket.config;

import com.jzj.common.utils.JwtUtils;

import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <p>
 * 连接数据存储
 * </p>
 *
 * @author Jzj
 * @since 2024/8/27 17:06
 */
public class Connect {

    private static final ConcurrentHashMap<String, UUID> storage = new ConcurrentHashMap<>();

    /**
     * 保存链接
     *
     * @param token     token
     * @param sessionId 连接id
     */
    public static void save(String token, UUID sessionId) {
        String userId = JwtUtils.getSubject(token, JwtUtils.USERID);
        if (userId != null && sessionId != null) {
            storage.put(userId, sessionId);
        }
    }

    /**
     * 客户端列表
     */
    public static Collection<UUID> list() {
        return storage.values();
    }
}
