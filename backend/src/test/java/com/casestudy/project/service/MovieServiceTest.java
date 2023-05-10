package com.casestudy.project.service;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.converter.MovieConverter;
import com.casestudy.project.dto.requests.CreateMovieRequest;
import com.casestudy.project.model.Movie;
import com.casestudy.project.repository.MovieRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class MovieServiceTest {

    private MovieRepository movieRepository;
    private MovieConverter movieConverter;

    private MovieService movieService;


    @BeforeEach
    void setUp() {
        movieRepository = Mockito.mock(MovieRepository.class);
        movieConverter = Mockito.mock(MovieConverter.class);
    }

    @Test
    public void testCreateMovie() {
        // Given
        CreateMovieRequest createMovieRequest = new CreateMovieRequest("111","2121","1321","tt1234567","2312321");
        Movie movie = new Movie();
        movie.setImdbId("tt1234567");
        when(movieRepository.findMovieByImdbId("tt1234567")).thenReturn(null);
        when(movieConverter.toModel(createMovieRequest)).thenReturn(movie);
        when(movieRepository.save(movie)).thenReturn(movie);
        when(movieConverter.toDto(movie)).thenReturn(new MovieDto());

        // When
        MovieDto result = movieService.create(createMovieRequest);

        // Then
        assertNotNull(result);
    }
}