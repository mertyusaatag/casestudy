package com.casestudy.project.repository;

import com.casestudy.project.model.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie,String> {

    Movie findMovieByTitleAndDirector(String title, String director);

    List<Movie> findMovieByType(String type);

}
