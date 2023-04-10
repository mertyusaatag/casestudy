package com.casestudy.project.dto.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UpdateMovieRequest {
    @NotNull
    @NotBlank
    private String title;
}
