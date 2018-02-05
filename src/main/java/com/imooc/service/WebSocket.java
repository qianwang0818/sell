package com.imooc.service;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet<WebSocket>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketSet.add(this);
        log.info("【WebSocket消息】有新的连接,当前连接数:{}",webSocketSet.size());
    }

    @OnClose
    public void onClose(Session session){
        webSocketSet.remove(this);
        log.info("【WebSocket消息】断开连接,当前连接数:{}",webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message){
        log.info("【WebSocket消息】收到客户端发来的消息:{}",message);
    }

    /**消息广播发送*/
    public void sendMessage(String message){
        for (WebSocket webSocket : webSocketSet) {
            log.info("【WebSocket消息】服务端广播消息,message:{}",message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                log.error("【WebSocket消息】广播消息发生异常:{}",e.getMessage());
                e.printStackTrace();
            }
        }
    }

}
