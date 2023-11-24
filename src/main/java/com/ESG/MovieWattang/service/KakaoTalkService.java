package com.ESG.MovieWattang.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.io.IOException;

@Service
public class KakaoTalkService {

    @Value("${kakao.access-token}")
    private String accessToken;

    private final WebClient webClient;

    public KakaoTalkService() {
        this.webClient = WebClient.builder()
                .baseUrl("https://kapi.kakao.com/v2/api/talk/memo/default/send")
                .defaultHeader("Content-Type", "application/x-www-form-urlencoded")
                .defaultHeader("Authorization", "Bearer " + accessToken)
                .build();
    }

    public void sendKakaoTalkMessage(String title) {
        String webUrl = "https://developers.kakao.com";

        String requestBody = "template_object={\"object_type\":\"text\",\"text\":\" D-1 영화: " + title + "\",\"link\":{\"web_url\":\"" + webUrl + "\"}}";

        Mono<String> response = webClient.post()
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .body(BodyInserters.fromValue(requestBody))
                .retrieve()
                .bodyToMono(String.class);

        response.doOnError(error -> {
            System.out.println("KakaoTalk 메세지 전송 중 오류 발생: " + error.getMessage());
        }).subscribe(
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
