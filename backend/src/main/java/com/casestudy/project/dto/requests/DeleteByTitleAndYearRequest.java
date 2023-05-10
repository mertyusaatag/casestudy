package com.casestudy.project.dto.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class DeleteByTitleAndYearRequest {
    @NotNull
    private String title;
    @NotNull
    private String director;

}
