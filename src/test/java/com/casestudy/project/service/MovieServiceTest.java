package com.casestudy.project.service;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.converter.MovieConverter;
import com.casestudy.project.dto.requests.DeleteByTitleAndYearRequest;
import com.casestudy.project.dto.requests.UpdateMovieRequest;
import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class MovieServiceTest {

    private MovieService movieService;
    private MovieRepository movieRepository;
    private MovieConverter movieConverter;

    @BeforeEach
    public void setUp(){
        movieRepository = Mockito.mock(MovieRepository.class);
        movieConverter = Mockito.mock(MovieConverter.class);

        movieService = new MovieService(movieRepository,movieConverter);
    }

    @Test
    void updateMovie_UpdateMovieAndReturnsDto(){
        String id = "exist_id";
        Movie movie = new Movie();
        movie.setId(id);

        UpdateMovieRequest updateMovieRequest = new UpdateMovieRequest("Avatar");


        List<Movie> movies = List.of(new Movie(id,"Film1","200","Alad"),
                new Movie("id2","Film2","200","Balad"),
                new Movie("id3","Film3","400","Tatat"));

        MovieDto movieDto = new MovieDto();
        movieDto.setId(id);

        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.ofNullable(movies.get(0)));
        //Mockito.when(movieService.findMovieById(id)).thenReturn(Optional.ofNullable(movies.get(0)));
        Mockito.when(movieRepository.save(movie)).thenReturn(movie);
        Mockito.when(movieConverter.toDto(movie)).thenReturn(movieDto);

        MovieDto result = movieService.updateMovie(id,updateMovieRequest);

        assertEquals(movieDto,result);

        Mockito.verify(movieRepository).findById(id);
        Mockito.verify(movieConverter).toDto(movies.get(0));
        Mockito.verify(movieRepository).save(movies.get(0));

    }

    @Test
    public void deleteById_deletesMovieAndReturnsDto() {
        //Arrange
        String id = "123";
        Movie movie = new Movie(
                id,"title","year","rated","runtime",
                "genre","director","writer","plot","lang",
                "count","awards","poster","metaSc","imdbRat","imdbVo",
                "imdbId","type",true,"relased","actor");
        movie.setId(id);
        movie.setTitle("deleted_title");
        Mockito.when(movieRepository.findById(id)).thenReturn(Optional.of(movie));

        MovieDto movieDto = new MovieDto("id","deleted_title","year","rated","runtime",
                "genre","director","writer","plot","lang",
                "count","awards","poster","metaSc","imdbRat","imdbVo",
                "imdbId","type",true,"relased","actor");

        Mockito.when(movieConverter.toDto(movie)).thenReturn(movieDto);


        MovieDto deletedMovieDto = movieService.deleteById(id);


        Mockito.verify(movieRepository, Mockito.times(1)).delete(movie);
        assertEquals(movie.getTitle(), deletedMovieDto.getTitle());
    }

    @Test
    public void findMovieByTitleAndDirector_deletesMovieAndReturnsDto() {
        //Arrange
        DeleteByTitleAndYearRequest request = new DeleteByTitleAndYearRequest("The Shawshank Redemption", "Frank Darabont");
        Movie movie = new Movie();
        MovieDto movieDto = new MovieDto();
        Mockito.when(movieRepository.findMovieByTitleAndDirector(request.getTitle(), request.getDirector())).thenReturn(movie);
        Mockito.when(movieConverter.toDto(movie)).thenReturn(movieDto);
        MovieDto deletedMovieDto = movieService.findMovieByTitleAndDirector(request);

        Mockito.verify(movieRepository, Mockito.times(1)).delete(movie);
        assertNotNull(deletedMovieDto);
        assertEquals(movie.getTitle(), deletedMovieDto.getTitle());
        assertEquals(movie.getDirector(), deletedMovieDto.getDirector());
    }


}