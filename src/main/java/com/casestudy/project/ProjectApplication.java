package com.casestudy.project;

import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import com.casestudy.project.service.JsonParserService;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.simple.parser.JSONParser;

import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	public ProjectApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	private final MovieRepository movieRepository;

	public static void main(String[] args){

		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JsonParserService.jsonParser(movieRepository);
	}

}
