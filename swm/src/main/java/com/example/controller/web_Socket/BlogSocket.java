package com.example.controller.web_Socket;

import com.example.service.GuidPool;
import com.example.service.SessionPool;
import com.example.service.api.BlogSocketApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;

@ServerEndpoint("/blog_api")
@Component
public class BlogSocket {
    private static BlogSocketApi blogSocketApi;
    private static SessionPool sessionPool;
    private static GuidPool guidPool;
    private static BlogJson blogJson;
    @Autowired
    public void setChatService(SessionPool sessionPool,GuidPool guidPool,BlogJson blogJson,BlogSocketApi blogSocketApi) {
        BlogSocket.sessionPool = sessionPool;
        BlogSocket.guidPool=guidPool;
        BlogSocket.blogJson=blogJson;
        BlogSocket.blogSocketApi=blogSocketApi;
    }
    public void sendMessage(Session session, String message) throws IOException {
        if(session != null){
            synchronized (session) {
                System.out.println("发送数据：" + message);
                session.getBasicRemote().sendText(message);
            }
        }
    }
    //建立连接成功调用
    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessionPool.put(session.getId(),session);
        sendMessage(session,blogSocketApi.getJsonGroupById("index",0));
        System.out.println("add用户id："+session.getId()+"当前用户数量"+sessionPool.getOnlineNum());
    }
    @OnClose
    public void onClose(Session session){
        sessionPool.remove(session.getId());
        System.out.println("sub用户id："+session.getId()+"当前用户数量"+sessionPool.getOnlineNum());
    }
    @OnMessage
    public void onMessage(Session session,String message) throws IOException {
        System.out.println("用户id"+session.getId()+"消息"+message);
    }
}
