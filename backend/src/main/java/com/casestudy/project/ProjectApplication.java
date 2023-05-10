package com.casestudy.project;

import com.casestudy.project.model.Movie;
import com.casestudy.project.service.MovieService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@SpringBootApplication
public class ProjectApplication{


	public static void main(String[] args){

		SpringApplication.run(ProjectApplication.class, args);

	}

	@Bean
	CommandLineRunner runner(MovieService movieService){
		return args -> {
			ObjectMapper mapper = new ObjectMapper();

			TypeReference<List<Movie>> typeReference = new TypeReference<List<Movie>>(){};
			InputStream inputStream = TypeReference.class.getResourceAsStream("/json/MovieJson.txt");
			try {
				List<Movie> movies = mapper.readValue(inputStream,typeReference);
				movieService.saveAll(movies);
				System.out.println("Movies Saved!");
			} catch (IOException e){
				System.out.println("Unable to save movies: " + e.getMessage());
			}
		};
	}



}
