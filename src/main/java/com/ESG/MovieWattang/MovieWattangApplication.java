package com.ESG.MovieWattang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;

@SpringBootApplication
public class MovieWattangApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(MovieWattangApplication.class, args);

		MovieDday movieDday = new MovieDday();
		movieDday.Dday();
	}

}
