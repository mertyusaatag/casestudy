package com.casestudy.project.dto.requests;

import lombok.Data;

@Data
public class FilterRequest {

    private String type;
    private float imdbRating;
    private String director;
}
