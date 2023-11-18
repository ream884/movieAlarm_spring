package com.ESG.MovieWattang.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class KakaoTalkService {

    private static final String accessToken = "eQ7a3MgQ1W3Eu79KZfqCWixtQnyJfWfm5s8KPXVaAAABi-E89jIe0jm_MNo9Pw";

    private final WebClient webClient;

    public KakaoTalkService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com/v2/api/talk/memo/default/send")
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                .defaultHeader("Authorization", "Bearer " + accessToken)
                .build();
    }

    public void sendKakaoTalkMessage() {
        String message = "테스트 메세지";


        String webUrl = "https://developers.kakao.com";
        String mobileWebUrl = "https://developers.kakao.com";
        String buttonTitle = "바로 확인";

        String requestBody = "template_object={\"object_type\":\"text\",\"text\":\"" + message + "\",\"link\":{\"web_url\":\"" + webUrl + "\",\"mobile_web_url\":\"" + mobileWebUrl + "\"},\"button_title\":\"" + buttonTitle + "\"}";

        Mono<String> response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class);

        response.subscribe(
                responseBody -> {
                    // 메시지 전송 성공
                    System.out.println("KakaoTalk 메시지 전송 성공");
                },
                error -> {
                    // 메시지 전송 실패
                    System.err.println("KakaoTalk 메시지 전송 실패: " + error.getMessage());
                }
        );
    }
}
