package com.ESG.MovieWattang.service;

import com.ESG.MovieWattang.FilterDday;
import com.ESG.MovieWattang.MovieCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
public class SchedulerService {

    Map<String, String> movieData = new HashMap<>();
    MovieCrawler movieCrawler = new MovieCrawler();
    FilterDday filterDday = new FilterDday();

    @Autowired
    KakaoTalkService kakaoTalkService;
    @Scheduled(cron = "0 33 18 24 * *")
    public void send() {
        try {
            movieData = filterDday.filterMoviesByDday(movieCrawler.Dday(), "D-1");
            movieData.forEach((title, dday) -> System.out.printf("D-1: " + title));
            movieData.forEach((title, dday) -> kakaoTalkService.sendKakaoTalkMessage(title));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
