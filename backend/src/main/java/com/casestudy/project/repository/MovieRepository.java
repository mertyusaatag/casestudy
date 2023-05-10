package com.casestudy.project.repository;

import com.casestudy.project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,String> {

    Movie findMovieByTitleAndDirector(String title, String director);


    List<Movie> findMovieByType(String type);

    Movie findMovieByImdbId(String imdbId);

    @Query("SELECT m FROM Movie m where m.title LIKE %?1%")
    List<Movie> search(String title);

}
