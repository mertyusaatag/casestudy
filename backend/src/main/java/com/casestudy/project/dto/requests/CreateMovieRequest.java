package com.casestudy.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMovieRequest {
    public CreateMovieRequest(String title, String director, String writer, String imdbId,String type) {
        this.title = title;
        this.director = director;
        this.writer = writer;
        this.imdbId = imdbId;
        this.type = type;
    }

    @NotBlank(message = "Title field cannot be empty")
    @NotNull(message = "Title field cannot be empty")
    private String title;
    private String years;
    private String rated;
    private String runTime;
    private String genre;
    @NotBlank(message = "Director field cannot be empty")
    @NotNull(message = "Director field cannot be empty")
    private String director;
    @NotBlank(message = "Writer field cannot be empty")
    @NotNull(message = "Writer field cannot be empty")
    private String writer;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metaScore;
    @NotBlank(message = "imdbRating field cannot be empty")
    @NotNull(message = "imdbRating field cannot be empty")
    private String imdbRating;
    private String imdbVotes;
    @NotBlank(message = "Imdb Id field cannot be empty")
    @NotNull(message = "Imdb Id field cannot be empty")
    private String imdbId;
    @NotBlank(message = "Type field cannot be empty")
    @NotNull(message = "Type field cannot be empty")
    private String type;
    private Boolean comingSoon;
    private String released;
    private String actors;
}
