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
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        Movie movie = movieRepository.findMovieByTitleAndDirector(request.getTitle().toLowerCase(),request.getDirector());

        if (movie == null) {
            throw new NotFoundException("Title and Year do not match any movie");
        }

        movieRepository.delete(movie);
        return movieConverter.toDto(movie);
    }

    public static int diffChecker(String str1, String str2){
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];

        for (int i = 0; i <= str1.length(); i++)
        {
            for (int j = 0; j <= str2.length(); j++) {
                if (i == 0) {
                    dp[i][j] = j;
                }

                else if (j == 0) {
                    dp[i][j] = i;
                }

                else {
                    dp[i][j] = minm_edits(dp[i - 1][j - 1]
                                    + NumOfReplacement(str1.charAt(i - 1),str2.charAt(j - 1)), // replace
                            dp[i - 1][j] + 1, // delete
                            dp[i][j - 1] + 1); // insert
                }
            }
        }

        return dp[str1.length()][str2.length()];
    }

    private static int NumOfReplacement(char c1, char c2)
    {
        return c1 == c2 ? 0 : 1;
    }

    private static int minm_edits(int... nums)
    {

        return Arrays.stream(nums).min().orElse(
                Integer.MAX_VALUE);
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
