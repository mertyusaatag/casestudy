package com.casestudy.project.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID" , strategy = "org.hibernate.id.UUIDGenerator")
    private String id;
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

    public Movie(String title2, String type, String year, String director) {
        this.title = title2;
        this.type = type;
        this.years = year;
        this.director = director;
    }
    public Movie(String id, String title, String year, String director,String type) {
        this.id = id;
        this.title = title;
        this.years = year;
        this.director = director;
        this.type = type;
    }
}
