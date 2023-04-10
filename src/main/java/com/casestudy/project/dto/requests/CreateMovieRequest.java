package com.casestudy.project.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CreateMovieRequest {
    @NotNull(message = "Title field cannot be empty")
    private String title;
    private String years;
    private String rated;
    private String runTime;
    private String genre;
    @NotNull(message = "Director field cannot be empty")
    private String director;
    @NotNull(message = "writer field cannot be empty")
    private String writer;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metaScore;
    @NotNull(message = "imdbRating field cannot be empty")
    private String imdbRating;
    private String imdbVotes;
    private String imdbId;
    @NotNull(message = "Type field cannot be empty")
    private String type;
    private Boolean comingSoon;
    private String released;
    private String actors;
}
