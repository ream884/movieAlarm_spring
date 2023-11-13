package com.example.movie;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;


public class MovieService {
    private String url = "http://www.cgv.co.kr/movies/pre-movies.aspx";
    private ArrayList movieList = new ArrayList<String>();

    public ArrayList getMovieList() throws IOException {
        Document document = Jsoup.connect(url).get();

        document.getElementsByClass("box-contents")
                .stream()
                .filter(element -> element.select("em.dday").text().equals("D-2"))
                .forEach(oneDayBeforeRelease ->
                        movieList.add(oneDayBeforeRelease.select("strong.title").text())
                );

        return movieList;
    }
}
