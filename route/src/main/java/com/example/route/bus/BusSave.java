package com.example.route.bus;//package com.example.projectdemo;
//
//import com.fasterxml.jackson.databind.JsonNode;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.*;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.UriComponents;
//import org.springframework.web.util.UriComponentsBuilder;
//
//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URI;
//import java.net.URL;
//import java.net.URLEncoder;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@RestController
//@RequiredArgsConstructor
//public class BusSave {
//    @GetMapping("/test")
//    public String apiTest() throws IOException {
//        StringBuilder urlBuilder = new StringBuilder("https://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=yduV9FlTASC1a3tG%2FCSLsdBID3HNgkSNt2iF6mtPMnJiMxL5KfeLCTmvKMAB8aS6nJPpZiEO5rCDGnAOOeuYkg%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
//        urlBuilder.append("&" + URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("300", "UTF-8")); /*한 페이지 결과 수*/
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
//        urlBuilder.append("&" + URLEncoder.encode("terminalNm", "UTF-8") + "=" + URLEncoder.encode("", "UTF-8")); /*터미널명*/
//        URL url = new URL(urlBuilder.toString());
//        System.out.println("-" + url + "-");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        return sb.toString();
//
//    }
//
//    @GetMapping("/test2")
//    public String apiTest2() throws IOException {
//        StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1613000/ExpBusInfoService/getCtyCodeList"); /*URL*/
//        urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8") + "=yduV9FlTASC1a3tG%2FCSLsdBID3HNgkSNt2iF6mtPMnJiMxL5KfeLCTmvKMAB8aS6nJPpZiEO5rCDGnAOOeuYkg%3D%3D"); /*Service Key*/
//        urlBuilder.append("&" + URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*데이터 타입(xml, json)*/
//        URL url = new URL(urlBuilder.toString());
//        System.out.println("-" + url + "-");
//        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
//        conn.setRequestMethod("GET");
//        conn.setRequestProperty("Content-type", "application/json");
//        System.out.println("Response code: " + conn.getResponseCode());
//        BufferedReader rd;
//        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
//            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
//        } else {
//            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
//        }
//        StringBuilder sb = new StringBuilder();
//        String line;
//        while ((line = rd.readLine()) != null) {
//            sb.append(line);
//        }
//        rd.close();
//        conn.disconnect();
//        return sb.toString();
//
//    }
//
//    private final RestTemplate restTemplate;
//    private final TerminalRepository terminalRepository;
//    private final RoutesRepository routesRepository;
//
//    @GetMapping("/test3")
//    public void apiTest3() throws IOException {
//        // 요청 URL 만들기
//        String apiUrl = "http://apis.data.go.kr/1613000/ExpBusInfoService/getExpBusTrminlList";
//        URI uri = UriComponentsBuilder.fromUriString(apiUrl)
//                .queryParam("serviceKey", "yduV9FlTASC1a3tG/CSLsdBID3HNgkSNt2iF6mtPMnJiMxL5KfeLCTmvKMAB8aS6nJPpZiEO5rCDGnAOOeuYkg==")
//                .queryParam("pageNo", 1)
//                .queryParam("numOfRows", 300)
//                .queryParam("_type", "json")
//                .queryParam("terminalNm", "")
//                .build()
//                .toUri();
//
//        // HTTP Header 생성
//        HttpHeaders headers = new HttpHeaders();
//        headers.setContentType(MediaType.APPLICATION_JSON);
//        headers.set("Accept-Charset", "utf-8");
//
//        // HTTP 요청 보내기 (GET 요청)
//        HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(
//                uri,
//                HttpMethod.GET,
//                requestEntity,
//                String.class
//        );
//
//        if (responseEntity.getStatusCode() == HttpStatus.OK) {
//
//            JsonNode jsonResponse = new ObjectMapper().readTree(responseEntity.getBody());
//            System.out.println(jsonResponse);
//            JsonNode items = jsonResponse.get("response").get("body").get("items").get("item");
//            int i = 0;
//            for (JsonNode item : items) {
//                String terminalNm = item.path("terminalNm").asText();
//                String terminalId = item.path("terminalId").asText();
//
//                i += 1;
//                System.out.println(terminalNm + "------" + i);
//                // 데이터베이스에 저장
//                boolean isTerminal = terminalRepository.existsByTerminalName(terminalNm);
//                if (isTerminal == false) {
//                    terminalRepository.save(new Terminal(terminalNm, terminalId));
//                }
//            }
//
//
//        } else {
//            System.out.println("error");
//        }
//
//
//    }
//
//    @GetMapping("/test4")
//    public void apiTest4() throws IOException {
//        // 요청 URL 만들기
//        String apiUrl = "https://apis.data.go.kr/1613000/ExpBusInfoService/getStrtpntAlocFndExpbusInfo";
//
//        List<Terminal> terminalList = terminalRepository.findAll();
//        System.out.println(terminalList.toString() + "-");
//        List<String> terminalNameList = terminalList.stream()
//                .map(Terminal::getTerminalCode)
//                .collect(Collectors.toList());
//
////        System.out.println(terminalNameList.toString()+"--");
//        for (String terminalCode : terminalNameList) {
////            System.out.println(terminalCode+"===");
//            UriComponents uri = UriComponentsBuilder.fromUriString(apiUrl)
//                    .queryParam("serviceKey", "yduV9FlTASC1a3tG/CSLsdBID3HNgkSNt2iF6mtPMnJiMxL5KfeLCTmvKMAB8aS6nJPpZiEO5rCDGnAOOeuYkg==")
//                    .queryParam("pageNo", 1)
//                    .queryParam("numOfRows", 30)
//                    .queryParam("_type", "json")
//                    .queryParam("depTerminalId", terminalCode)
//                    .queryParam("depPlandTime", "20230401")
//                    .build();
//
//            // HTTP Header 생성
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Accept-Charset", "utf-8");
//
//            // HTTP 요청 보내기 (GET 요청)
//            HttpEntity<Object> requestEntity = new HttpEntity<>(headers);
//
//            ResponseEntity<String> responseEntity = restTemplate.exchange(
//                    uri.toUriString(),
//                    HttpMethod.GET,
//                    requestEntity,
//                    String.class
//            );
//
//
//            if (responseEntity.getStatusCode() == HttpStatus.OK) {
//
//                JsonNode jsonResponse = new ObjectMapper().readTree(responseEntity.getBody());
////                return jsonResponse;
////                System.out.println(jsonResponse);
//                JsonNode items = jsonResponse.get("response").get("body").get("items").get("item");
////                int i = 0;
//                if (items != null) {
//                    for (JsonNode item : items) {
//                        System.out.println(item);
//                        String terminalNm = item.path("arrPlaceNm").asText();
//
//                        Terminal a = terminalRepository.findByTerminalCode(terminalCode);
//                        Terminal b = terminalRepository.findByTerminalName(terminalNm);
//
//                        System.out.println("-----test----"+a+","+b);
//                        boolean isTerminal = routesRepository.existsByDepartureTerminalAndDestinationTerminal(a,b);
//                        // 데이터베이스에 저장
//                        if (b != null && isTerminal == false) {
//                            Routes route = new Routes(a, b); // Routes 엔티티 생성
//                            routesRepository.save(route);
//                        }
//
//
//                    }
//                }
//
//            } else {
//                System.out.println("error");
//            }
//            }
//        }
//
//    }
//

