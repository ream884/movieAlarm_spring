package com.ESG.MovieWattang;

import lombok.extern.slf4j.Slf4j;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Service
public class MovieCrawler {

    public Map Dday() throws IOException {

        String url = "http://www.cgv.co.kr/movies/pre-movies.aspx";

        Document doc = Jsoup.connect(url).get();
        Elements boxContentsElements = doc.select("div.box-contents");

        Map<String, String> map = boxContentsElements.stream()
                .collect(Collectors.toMap(
                        element -> element.select("strong.title").text(),
                        element -> element.select("em.dday").text(),
                        (existing, replacement) -> existing
                ));

        return map;
    }
}
