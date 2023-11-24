package com.ESG.MovieWattang;

import java.util.Map;
import java.util.stream.Collectors;

public class FilterDday {

    public Map<String, String> filterMoviesByDday(Map<String, String> movies, String dDay) {
        return movies.entrySet().stream()
                .filter(stringStringEntry -> dDay.equals(stringStringEntry.getValue()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
