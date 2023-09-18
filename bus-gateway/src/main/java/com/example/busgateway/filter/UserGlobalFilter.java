package com.example.busgateway.filter;

import com.example.busgateway.dto.UserDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;


/*
 이 클래스의 주요 역할은 글로벌 레벨에서 요청과 응답을 가로채서 로깅 또는 다른 전처리 및 후처리 작업을 수행하는 것,
 설정된 UserDto 객체를 통해 로깅이 활성화되어 있는 경우 요청 및 응답을 출력하며, JWT 시크릿 값을 사용할 수 있다.
  이렇게하여 모든 요청과 응답에 대해 공통된 로직을 쉽게 적용할 수 있다.
 */

@Component
public class UserGlobalFilter
        extends AbstractGatewayFilterFactory<UserDto> {
    @Value("${jwt.secret}")
    private String secret;
    public UserGlobalFilter() {
        super(UserDto.class);
    }
    @Override
    public GatewayFilter apply(UserDto config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            System.out.println("============ : " + secret);
            if(config.getLogging()){
                System.out.println("req : " + request.getId() +
                        ", " + request.getMethod() + " : "
                        + request.getPath() +
                        ", " + request.getRemoteAddress()
                );
            }
            return chain.filter(exchange).then(
                    Mono.fromRunnable(()->{
                        if(config.getLogging()){
                            System.out.println("res : " + request.getId() +
                                    ", " + response.getStatusCode()
                            );
                        }
                    })
            );
        };
    }
}