package com.casestudy.project.dto;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MovieDto {

    private String title;
    private String years;
    private String rated;
    private String runTime;
    private String genre;
    private String director;
    private String writer;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metaScore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbId;
    private String type;
    private Boolean comingSoon;
    private String released;
    private String actors;


}
