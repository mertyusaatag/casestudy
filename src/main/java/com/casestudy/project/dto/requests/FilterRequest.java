package com.casestudy.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class FilterRequest {

    private String type;
    private float imdbRating;
    private String director;
}
