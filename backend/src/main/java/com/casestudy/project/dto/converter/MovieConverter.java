package com.casestudy.project.dto.converter;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.requests.CreateMovieRequest;
import com.casestudy.project.dto.response.GeneralMovieResponse;
import com.casestudy.project.model.Movie;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MovieConverter {


    public Movie toModel(CreateMovieRequest createMovieRequest){
       return Movie.builder()
                .title(createMovieRequest.getTitle())
                .years(createMovieRequest.getYears())
                .rated(createMovieRequest.getRated())
                .runTime(createMovieRequest.getRunTime())
                .genre(createMovieRequest.getGenre())
                .director(createMovieRequest.getDirector())
                .writer(createMovieRequest.getWriter())
                .plot(createMovieRequest.getPlot())
                .language(createMovieRequest.getLanguage())
                .country(createMovieRequest.getCountry())
                .awards(createMovieRequest.getAwards())
                .poster(createMovieRequest.getPoster())
                .metaScore(createMovieRequest.getMetaScore())
                .imdbRating(createMovieRequest.getImdbRating())
                .imdbVotes(createMovieRequest.getImdbVotes())
                .imdbId(createMovieRequest.getImdbId())
                .type(createMovieRequest.getType())
                .comingSoon(createMovieRequest.getComingSoon())
                .released(createMovieRequest.getReleased())
                .actors(createMovieRequest.getActors())
                .build();
    }

    public MovieDto toDto(Movie movie){
        return MovieDto.builder()
                .title(movie.getTitle())
                .years(movie.getYears())
                .rated(movie.getRated())
                .runTime(movie.getRunTime())
                .genre(movie.getGenre())
                .director(movie.getDirector())
                .writer(movie.getWriter())
                .plot(movie.getPlot())
                .language(movie.getLanguage())
                .country(movie.getCountry())
                .awards(movie.getAwards())
                .poster(movie.getPoster())
                .metaScore(movie.getMetaScore())
                .imdbRating(movie.getImdbRating())
                .imdbVotes(movie.getImdbVotes())
                .imdbId(movie.getImdbId())
                .type(movie.getType())
                .comingSoon(movie.getComingSoon())
                .released(movie.getReleased())
                .actors(movie.getActors())
                .build();
    }

    public GeneralMovieResponse toResponse(MovieDto movieDto)
    {
       return GeneralMovieResponse.builder().title(movieDto.getTitle()).build();
    }

    public List<MovieDto> toList(List<Movie> fromModel){
        return fromModel.stream().map(this ::toDto).collect(Collectors.toList());
    }
}
