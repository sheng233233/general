package edu.zut.service;


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

public class WebSocket {

    private Session session;

    private static CopyOnWriteArraySet<WebSocket> webSocketset = new CopyOnWriteArraySet<>();

    @OnOpen
    public void onOpen(Session session){
        this.session = session;
        webSocketset.add(this);
    }

    @OnClose
    public void onClose(){
        webSocketset.remove(this);
    }

    @OnMessage
    public void onMessage(String message){
        System.out.println(message);
    }

    public void sendMessage(String message){
        for (WebSocket webSocket:webSocketset) {
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }


}
