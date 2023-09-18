package com.example.busgateway.filter;

import com.example.busgateway.dto.UserDto;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
/*
이 필터는 API 요청에 대한 캐싱 및 통계 수집을 수행하며,
 필터 체인을 통해 실제 요청을 처리하기 전과 후에 작업을 수행
 이런 종류의 필터를 사용하면 API 요청 및 응답 데이터를 추적하고 분석할 수 있으며,
  캐싱을 통해 중복 요청을 줄이는 등의 성능 향상을 이룰 수 있음

 */
@Component
public class CacheFilter
        extends AbstractGatewayFilterFactory<UserDto> {
    private final Map<String, cashData> map = new HashMap();

    public CacheFilter() {
        super(UserDto.class);
    }

    /*
    !!map: map은 데이터를 저장하고 검색하기 위한 목적
    map에 데이터를 저장할 때, 키(key)와 값(value)은 모두 문자열로 저장되어야 한다.
    request.getPath()가 반환하는 값이 문자열이 아닌 경우에도 문자열로 변환하여 사용한다.
    그래서 map에 저장하려면 .toString()을 붙이는 거임
    getOrDefault: map에 인터페이스 메서드 중 하나로 만약 해당 키가 존재하지 않을 경우 기본값(default value)을 반환하는 기능을 수행
     */
    @Override
    public GatewayFilter apply(UserDto config) {
        return ((exchange, chain) -> {
            ServerHttpRequest request = exchange.getRequest();
            request.getMethod();
            request.getPath();
            map.put(request.getPath().toString()
                    , map.getOrDefault(request.getPath().toString(),
                            new cashData(request.getPath().toString(),
                                    request.getMethod())));
            ServerHttpResponse response = exchange.getResponse();
            HttpStatusCode statusCode = response.getStatusCode();
            System.out.println(statusCode);
            // return chain.filter(exchange).then(...) 코드 조각은 비동기 작업을 수행하는 부분
            /* chain.filter(exchange): 이 부분은 현재 필터가 실행 중인 HTTP 요청을 처리하고, 필터 체인의 다음 단계로 요청을 전달,
                이 호출은 비동기 작업 시작하는거임 */
            /*then(...): then 메서드는 비동기 작업이 완료될 때 실행할 작업을 지정되고,
              작업은 비동기 작업이 완료되면 실행되며, 작업이 완료될 때까지 블로킹되지 않는다.
            */
            // 따라서 return chain.filter(exchange).then(...)는 현재 필터에서 HTTP 요청을 처리하고,
            // 비동기 작업이 완료되면 특정 작업을 수행하는 구조를 갖고 있으며, 이것이 비동기 처리를 구현하는 일반적인 패턴 중 하나
            return chain.filter(exchange).then(
                    Mono.fromRunnable(() -> {
                        System.out.println(map.get(request.getPath().toString()));
                        System.out.println((response.setComplete()));
                    })
            );
        });
    }


// API 요청에 대한 캐시 정보를 저장하고 관리하는 데 사용되며, 요청 횟수, 생성 날짜, API 경로,
// HTTP 메서드와 같은 정보를 추적합니다. 따라서 API 요청을 캐시하고 관리하기 위한 구조를 제공
    class cashData{
        private Integer count;
        private final Date date;
        private final String api;
        private final HttpMethod method;

        public cashData(String api, HttpMethod method) {
            this.count = 1;
            this.date = new Date();
            this.api = api;
            this.method = method;
        }

        public void plus(){
            this.count += 1;
        }
        @Override
        public String toString(){
            return "count : " +count;
        }
    }
}
