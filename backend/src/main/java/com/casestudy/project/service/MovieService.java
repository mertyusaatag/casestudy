package com.casestudy.project.service;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.converter.MovieConverter;
import com.casestudy.project.dto.requests.CreateMovieRequest;
import com.casestudy.project.dto.requests.DeleteByTitleAndYearRequest;
import com.casestudy.project.dto.requests.FilterRequest;
import com.casestudy.project.dto.requests.UpdateMovieRequest;
import com.casestudy.project.exception.AlreadyExistException;
import com.casestudy.project.exception.NotFoundException;
import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;

import static com.casestudy.project.service.FuzzySearch.diffChecker;

@Service
@RequiredArgsConstructor
public class MovieService {

    private final MovieRepository movieRepository;
    private final MovieConverter movieConverter;

    public MovieDto create(CreateMovieRequest createMovieRequest)
    {
        Movie movie = movieRepository.findMovieByImdbId(createMovieRequest.getImdbId());
        if(movie == null){
            return movieConverter.toDto(movieRepository.save(movieConverter.toModel(createMovieRequest)));
        }
        else{
            throw new AlreadyExistException("Imdb id is already exist");
        }
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

    public Movie findMovieById(String id)
    {
       return movieRepository.findById(id)
                .orElseThrow(()-> new NotFoundException("Account not found"));
    }

    public void saveAll(List<Movie> movies)
    {
        movieRepository.saveAll(movies);
    }

//    public MovieDto updateMovie(String id, UpdateMovieRequest request)
//    {
//        Movie movie = findMovieById(id);
//
//        movie.setTitle(request.getTitle() == null ? movie.getTitle() : request.getTitle());
//        movie.setYears(request.getYear() == null ? movie.getYears() : request.getYear());
//        movie.setGenre(request.getGenre() == null ? movie.getGenre() : request.getGenre());
//        movie.setLanguage(request.getLanguage()== null ? movie.getLanguage() : request.getLanguage());
//        movie.setPlot(request.getPlot()== null ? movie.getPlot() : request.getPlot());
//        movie.setActors(request.getActors()== null ? movie.getActors() : request.getActors());
//        movie.setImdbRating(request.getImdbRating()== null ? movie.getImdbRating() : request.getImdbRating());
//
//        return movieConverter.toDto(movieRepository.save(movie));
//    }

    public MovieDto updateMovieByImdbId(String imdbId, UpdateMovieRequest request)
    {
        Movie movie = movieRepository.findMovieByImdbId(imdbId);

        movie.setTitle((request.getTitle() == null || request.getTitle().isEmpty()) ? movie.getTitle() : request.getTitle());
        movie.setYears((request.getYear() == null || request.getYear().isEmpty()) ? movie.getYears() : request.getYear());
        movie.setGenre((request.getGenre() == null || request.getGenre().isEmpty()) ? movie.getGenre() : request.getGenre());
        movie.setLanguage((request.getLanguage() == null || request.getLanguage().isEmpty()) ? movie.getLanguage() : request.getLanguage());
        movie.setPlot((request.getPlot()== null || request.getPlot().isEmpty()) ? movie.getPlot() : request.getPlot());
        movie.setActors((request.getActors()== null || request.getActors().isEmpty()) ? movie.getActors() : request.getActors());
        movie.setImdbRating((request.getImdbRating()== null || request.getImdbRating().isEmpty()) ? movie.getImdbRating() : request.getImdbRating());

        return movieConverter.toDto(movieRepository.save(movie));
    }

    public MovieDto deleteById(String id) {
        Movie movie = findMovieById(id);
        movieRepository.delete(movie);
        return movieConverter.toDto(movie);
    }

    public MovieDto deleteByImdbId(String imdbId){
        Movie movie = movieRepository.findMovieByImdbId(imdbId);

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

        long startTime = System.currentTimeMillis();

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
        long endTime = System.currentTimeMillis();
        System.out.println("with levensthein algorithm : " + (endTime-startTime)  + "ms");
        return movieConverter.toDto(movie);
    }

    public MovieDto findByImdbId(String imdbId)
    {
        Movie movie = movieRepository.findMovieByImdbId(imdbId);

        System.out.println(movie);
        return movieConverter.toDto(movie);
    }

    public List<MovieDto> findMovieByQuerySearch(String title){
        long startTime = System.currentTimeMillis();

        List<Movie> movies = movieRepository.search(title);

        long endTime = System.currentTimeMillis();
        System.out.println("with query : " + (endTime-startTime)  + "ms");
        return movieConverter.toList(movies);

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
