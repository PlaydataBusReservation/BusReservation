package com.example.busgateway.filter;

import com.example.busgateway.config.JwtService;
import com.example.busgateway.dto.UserDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.List;


/*
TokenFilter 클래스는 주로 JWT 토큰의 유효성을 검사하고, 유효하지 않은 토큰으로부터 보호하기 위함.
 POST 요청에 대해서만 토큰 검증을 수행하며, 유효하지 않은 토큰이면 요청을 거부하고 응답을 반환,
 이를 통해 보안 및 권한 관리에 대한 중요한 역할을 수행한다.
 */
@Component
public class TokenFilter
        extends AbstractGatewayFilterFactory<UserDto> {
    public TokenFilter(JwtService jwtService) {
        super(UserDto.class);
        this.jwtService = jwtService;
    }
    private final JwtService jwtService;
    @Override
    public GatewayFilter apply(UserDto config) {
        return (exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            ServerHttpResponse response = exchange.getResponse();
            if(request.getMethod() == HttpMethod.POST
                    && !isValidToken(request))
                return onError(response,"401", HttpStatus.UNAUTHORIZED);
            return chain.filter(exchange);
        };
    }
    public boolean isValidToken(ServerHttpRequest request){
        List<String> authorization =
                request.getHeaders().get("Authorization");
        if(authorization == null || authorization.size() == 0) return false;
        String bearerToken = authorization.get(0);
        if( bearerToken.startsWith("Bearer ")){
            return jwtService
                    .parseToken(
                            bearerToken.replace("Bearer ",""));
        }
        return false;
    }
    public Mono<Void> onError(ServerHttpResponse response,
                              String msg, HttpStatus code){
        response.setStatusCode(code);
        return response.setComplete();
    }


}