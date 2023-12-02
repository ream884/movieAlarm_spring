package com.ESG.MovieWattang;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class MovieDday {

    public void Dday() throws IOException {

        String url = "http://www.cgv.co.kr/movies/pre-movies.aspx";

        String title;
        String dday;

//        Map<String, String> 이렇게 하기 <영화제목, 디데이>
        Map<String, String > map = new HashMap<>();

        Document doc = Jsoup.connect(url).get();

        Elements boxContentsElements = doc.select("div.box-contents");
        Elements titleElements = boxContentsElements.select("a>strong.title");
        Elements ddayElements = boxContentsElements.select("span.txt-info>strong>em.dday");

        for (int i=0; i<ddayElements.size(); i++) {
            title = titleElements.get(i).text();
            dday = ddayElements.get(i).text();

            map.put(title, dday);
        }


        for (Map.Entry<String, String> entry : map.entrySet()) {
            if (entry.getValue().equals("D-1")) {
                log.info("D-1 title: "+ entry.getKey());
            }

//            HttpClient // 사용자 입장
//            HttpRequest
        }
    }
}
