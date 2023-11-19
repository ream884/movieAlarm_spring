package com.example.movie;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

public class MessageService {
    private String url = "https://kapi.kakao.com/v2/api/talk/memo/default/send";

    public void send(ArrayList movieTitles) {
        String body = ""
                + "template_object={\n"
                + "        \"object_type\": \"text\",\n"
                + "        \"text\": \"" + movieTitles.toString() + "\",\n"
                + "        \"link\": {\n"
                + "            \"web_url\": \"http://www.cgv.co.kr/movies/pre-movies.aspx\",\n"
                + "            \"mobile_web_url\": \"http://www.cgv.co.kr/movies/pre-movies.aspx\"\n"
                + "        }\n"
                + "    }"
                + "";

        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .headers(
                        "Content-Type", "application/x-www-form-urlencoded",
                        "Authorization", "Bearer qxUf2ZnjW0aWdfzDWhHj3EMs9ywXmEHLGQYKPXTbAAABi5-aEZ8tjdRiIM79qQ"
                ).POST(HttpRequest.BodyPublishers.ofString(body))
                .build();

        client.sendAsync(request, HttpResponse.BodyHandlers.ofString());
    }
}
