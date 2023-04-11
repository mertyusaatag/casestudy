package com.casestudy.project.service;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.converter.MovieConverter;
import com.casestudy.project.dto.requests.CreateMovieRequest;
import com.casestudy.project.dto.requests.DeleteByTitleAndYearRequest;
import com.casestudy.project.dto.requests.FilterRequest;
import com.casestudy.project.dto.requests.UpdateMovieRequest;
import com.casestudy.project.exception.GeneralExceptionHandler;
import com.casestudy.project.exception.NotFoundException;
import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.casestudy.project.service.FuzzySearch.diffChecker;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    public MovieDto create(CreateMovieRequest createMovieRequest)
    {
        return movieConverter.toDto(movieRepository.save(movieConverter.toModel(createMovieRequest)));
    }

    public List<MovieDto> getAll() {
        return movieConverter.toList(movieRepository.findAll());
    }

    public List<MovieDto> getByType(String type)
    {
        List<Movie> movies = movieRepository.findMovieByType(type);

        if(movies.isEmpty()){
            throw new NotFoundException("There is no a movie/series with " + type);
        }

        return movieConverter.toList(movies);
    }

    private Movie findMovieById(String id)
    {
       return movieRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Account not found"));
    }

    public MovieDto updateMovie(String id, UpdateMovieRequest request)
    {
        Movie movie = findMovieById(id);
        movie.setTitle(request.getTitle());

        return movieConverter.toDto(movieRepository.save(movie));
    }

    public MovieDto deleteById(String id) {
        Movie movie = findMovieById(id);
        movieRepository.delete(movie);
        return movieConverter.toDto(movie);
    }

    public MovieDto findMovieByTitleAndDirector(DeleteByTitleAndYearRequest request) {
        Movie movie = movieRepository.findMovieByTitleAndDirector(request.getTitle(),request.getDirector());

        if (movie == null) {
            throw new NotFoundException("Title and Year do not match any movie");
        }

        movieRepository.delete(movie);
        return movieConverter.toDto(movie);
    }

    public MovieDto findByTitle(String name)
    {
        List<Movie> movies = movieRepository.findAll();
        int minDistance = Integer.MAX_VALUE;
        Movie movie = null;


        for (Movie m : movies)
        {
            int diff = diffChecker(m.getTitle().toLowerCase(),name.toLowerCase().replaceAll("//s","") );
            if(diff < minDistance){
                minDistance = diff;
                movie = m;
           }
        }

        return movieConverter.toDto(movie);
    }

    public List<MovieDto> filterBy(FilterRequest filterRequest)
    {
        List<Movie> movies = movieRepository.findAll()
            .stream()
            .filter(movie -> !movie.getImdbRating().equals("N/A") &&
                    Float.parseFloat(movie.getImdbRating()) >= filterRequest.getImdbRating())
            .filter(movie -> filterRequest.getDirector() == null || movie.getDirector().equals(filterRequest.getDirector()))
            .filter(movie -> filterRequest.getType() == null || movie.getType().equals(filterRequest.getType()))
            .toList();

        return movieConverter.toList(movies);
    }
}
