package com.example.reservation.contorller;

import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketMessage;
import org.springframework.web.socket.WebSocketSession;

import java.io.IOException;
import java.security.Principal;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Controller
public class WebSocketController {
    private Map<String, Set<WebSocketSession>> busSubscriptions = new HashMap<>();

    @SubscribeMapping("/subscribe/{busid}")
    public void subscribe(@DestinationVariable String busid, WebSocketSession session) {

        // 클라이언트가 구독한 버스에 대한 정보를 저장
        if (busSubscriptions.containsKey(busid))
            busSubscriptions.get(busid).add(session);
        else {
            Set<WebSocketSession> set = new HashSet<>();
            set.add(session);
            busSubscriptions.put(busid, set);
        }

    }

    @MessageMapping("/send/{busid}")
    public void sendMessage(@DestinationVariable String channel, String message) {
        // 클라이언트가 특정 채널로 메시지를 보낼 때 호출되는 메서드

        // 채널에 해당하는 세션 세트 가져오기
        Set<WebSocketSession> channelSessions = busSubscriptions.get(channel);

        if (channelSessions != null) {
            // 채널에 연결된 모든 클라이언트에게 메시지 보내기
            for (WebSocketSession session : channelSessions) {
                if (session.isOpen()) {
                    try {
                        session.sendMessage(new TextMessage(message));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    @MessageMapping("/unsubscribe/{busid}")
    public void unsubscribe(@DestinationVariable String busid, WebSocketSession session){
        if (busSubscriptions.containsKey(busid))
        {
            Set<WebSocketSession> sessions = busSubscriptions.get(busid);
            sessions.remove(session);
            if (sessions.size() == 0)
                busSubscriptions.remove(busid);
        }
    }
}
