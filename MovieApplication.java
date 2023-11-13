package com.example.movie;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import java.io.IOException;
import java.util.ArrayList;

@SpringBootApplication
@EnableScheduling
public class MovieApplication {
    public static void main(String[] args) throws IOException {
        SpringApplication.run(MovieApplication.class);


        scheduler();
    }

    @Scheduled(cron = "0/5 * * * * *")
    public static void scheduler() throws IOException {
        MovieService movieService = new MovieService();

        ArrayList movieList = movieService.getMovieList();

        System.out.println(movieList);
    }
}