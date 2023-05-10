package com.casestudy.project.dto.requests;

import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateMovieRequest {

    @Nullable
    private String title;

    @Nullable
    private String year;

    @Nullable
    private String Genre;

    @Nullable
    private String language;

    @Nullable
    private String plot;

    @Nullable
    private String actors;

    @Nullable
    private String imdbRating;
}
