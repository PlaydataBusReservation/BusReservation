package com.example.route.global;



import com.example.route.route.Route;
import com.example.route.route.RouteRepository;
import com.example.route.terminal.Terminal;
import com.example.route.terminal.TerminalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/setting")
public class SettingController {

    private final RestTemplate restTemplate;
    private final TerminalRepository terminalRepository;
    private final RouteRepository routeRepository;

    private ResponseEntity<String> apiSend(String apiUrl, int number, String terminalCode) {
        UriComponents uri = UriComponentsBuilder.fromUriString(apiUrl)
                .queryParam("serviceKey", "yduV9FlTASC1a3tG/CSLsdBID3HNgkSNt2iF6mtPMnJiMxL5KfeLCTmvKMAB8aS6nJPpZiEO5rCDGnAOOeuYkg==")
                .queryParam("pageNo", 1)
                .queryParam("numOfRows", number)
                .queryParam("_type", "json")
                .queryParam("depTerminalId", terminalCode)
                .queryParam("depPlandTime", "20230401")
                .build();

        // HTTP Header 생성
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Accept-Charset", "utf-8");

        // HTTP 요청 보내기 (GET 요청)
        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);

        ResponseEntity<String> responseEntity = restTemplate.exchange(
                uri.toUriString(),
                HttpMethod.GET,
                requestEntity,
                String.class
        );

        return responseEntity;
    }

    @GetMapping("/terminal")
    public void apiTest3() throws IOException {
        // 요청 URL 만들기
        String apiUrl = "http://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList";
        ResponseEntity<String> responseEntity = apiSend(apiUrl, 300, "");

        if (responseEntity.getStatusCode() == HttpStatus.OK) {

            JsonNode jsonResponse = new ObjectMapper().readTree(responseEntity.getBody());
            System.out.println(jsonResponse);
            JsonNode items = jsonResponse.get("response").get("body").get("items").get("item");
            int i = 0;
            for (JsonNode item : items) {
                String terminalNm = item.path("terminalNm").asText();
                String terminalId = item.path("terminalId").asText();

                i += 1;
                System.out.println(terminalNm + "------" + i);
                // 데이터베이스에 저장
                boolean isTerminal = terminalRepository.existsByTerminalName(terminalNm);
                if (isTerminal == false) {
                    terminalRepository.save(new Terminal(terminalNm, terminalId));
                }
            }


        } else {
            System.out.println("error");
        }


    }

    @GetMapping("/route")
    public void apiTest4() throws IOException {
        // 요청 URL 만들기
        String apiUrl = "https://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo";


        List<Terminal> terminalList = terminalRepository.findAll();
        List<String> terminalNameList = terminalList.stream()
                .map(Terminal::getTerminalCode)
                .collect(Collectors.toList());

        for (String terminalCode : terminalNameList) {

            ResponseEntity<String> responseEntity = apiSend(apiUrl, 50, terminalCode);


            if (responseEntity.getStatusCode() == HttpStatus.OK) {

                JsonNode jsonResponse = new ObjectMapper().readTree(responseEntity.getBody());
                JsonNode items = jsonResponse.get("response").get("body").get("items").get("item");
                if (items != null) {
                    for (JsonNode item : items) {
                        System.out.println(item);
                        String terminalNm = item.path("arrPlaceNm").asText();

                        Terminal a = terminalRepository.findByTerminalCode(terminalCode);
                        Terminal b = terminalRepository.findByTerminalName(terminalNm);

                        System.out.println("-----test----" + a + "," + b);
                        boolean isTerminal = routeRepository.existsByDepartureTerminalAndDestinationTerminal(a, b);
                        // 데이터베이스에 저장
                        if (b != null && isTerminal == false) {
                            Route route = new Route(a, b); // Routes 엔티티 생성
                            routeRepository.save(route);
                        }


                    }
                }

            } else {
                System.out.println("error");
            }
        }
    }
}
