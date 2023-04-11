package com.casestudy.project;

import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.json.simple.parser.JSONParser;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

@SpringBootApplication
public class ProjectApplication implements CommandLineRunner {

	public ProjectApplication(MovieRepository movieRepository) {
		this.movieRepository = movieRepository;
	}

	private final MovieRepository movieRepository;

	public void inserter() throws IOException, ParseException {
		JSONParser jsonParser = new JSONParser();

		JSONArray jsonArray = (JSONArray) jsonParser.parse(new FileReader("src/main/java/com/casestudy/project/MovieJson.txt"));

		for(Object object : jsonArray) {
			JSONObject record = (JSONObject) object;

			Movie movie = new Movie();
			movie.setComingSoon((Boolean) record.get("ComingSoon"));
			movie.setTitle((String) record.get("Title"));
			movie.setYears((String) record.get("Year"));
			movie.setRated((String) record.get("Rated"));
			movie.setReleased((String) record.get("Released"));
			movie.setRunTime((String) record.get("Runtime"));
			movie.setGenre((String) record.get("Genre"));
			movie.setDirector((String) record.get("Director"));
			movie.setWriter((String) record.get("Writer"));
			movie.setActors((String) record.get("Actors"));
			movie.setPlot((String) record.get("Plot"));
			movie.setLanguage((String) record.get("Language"));
			movie.setCountry((String) record.get("Country"));
			movie.setAwards((String) record.get("Awards"));
			movie.setPoster((String) record.get("Poster"));
			movie.setMetaScore((String) record.get("Metascore"));
			movie.setImdbRating((String) record.get("imdbRating"));
			movie.setImdbVotes((String) record.get("imdbVotes"));
			movie.setImdbId((String) record.get("imdbID"));
			movie.setType((String) record.get("Type"));

			movieRepository.save(movie);
		}
	}

	public static void main(String[] args){

		SpringApplication.run(ProjectApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		inserter();
	}

}
