package com.example.reservation.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        // 메시지 브로커 구성
        config.enableSimpleBroker("/topic"); // "/topic" 프리픽스로 시작하는 대상에게 메시지를 전송
        config.setApplicationDestinationPrefixes("/app"); // "/app" 프리픽스로 시작하는 메시지 핸들러로 라우팅
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        // 웹 소켓 엔드포인트 등록
        registry.addEndpoint("/ws").withSockJS(); // 클라이언트가 "/ws"로 연결
    }
}
