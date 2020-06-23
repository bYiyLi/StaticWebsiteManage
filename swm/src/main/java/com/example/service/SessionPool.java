package com.example.service;

import org.springframework.stereotype.Service;

import javax.websocket.Session;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
@Service("sessionPool")
public class SessionPool {
    private final AtomicInteger onlineNum = new AtomicInteger();
    //concurrent包的线程安全Set，用来存放每个客户端对应的WebSocketServer对象。
    private  final ConcurrentHashMap<String, Session> sessionPools = new ConcurrentHashMap<>();
    public Session get(String guid){
        return sessionPools.get(guid);
    }
    public void put( String guid,Session session){
        onlineNum.incrementAndGet();
        sessionPools.put(guid, session);
    }

    public int getOnlineNum() {
        return onlineNum.intValue();
    }

    public void remove(String guid){
        onlineNum.decrementAndGet();
        sessionPools.remove(guid);
    }
}
