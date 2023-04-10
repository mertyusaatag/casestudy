package com.casestudy.project.controller;

import com.casestudy.project.dto.MovieDto;
import com.casestudy.project.dto.converter.MovieConverter;
import com.casestudy.project.dto.requests.CreateMovieRequest;
import com.casestudy.project.dto.requests.DeleteByTitleAndYearRequest;
import com.casestudy.project.dto.requests.FilterRequest;
import com.casestudy.project.dto.requests.UpdateMovieRequest;
import com.casestudy.project.dto.response.GeneralMovieResponse;
import com.casestudy.project.service.MovieService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie")
@RequiredArgsConstructor
@Validated
public class MovieController {

    private final MovieService movieService;
    private final MovieConverter movieConverter;

    @PostMapping
    public ResponseEntity<GeneralMovieResponse> create(@RequestBody @Valid CreateMovieRequest createMovieRequest)
    {
        return ResponseEntity.ok(movieConverter.toResponse(movieService.create(createMovieRequest)));
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll()
    {
        return ResponseEntity.ok(movieService.getAll());
    }

    @GetMapping("/{type}")
    public ResponseEntity<List<MovieDto>> getByType(@PathVariable("type") String type)
    {
        return ResponseEntity.ok().body(movieService.getByType(type));
    }


    @PatchMapping("/{id}")
    public ResponseEntity<GeneralMovieResponse> update(@PathVariable("id") String id, @Valid @RequestBody UpdateMovieRequest request)
    {
        return ResponseEntity.ok(movieConverter.toResponse(movieService.updateMovie(id,request)));
    }

    @DeleteMapping
    public ResponseEntity<String> deleteByTitleAndYear(@Valid @RequestBody DeleteByTitleAndYearRequest request) {

        movieService.findMovieByTitleAndDirector(request);

        return ResponseEntity.ok().body(request.getTitle()+" Deleted.");
    }

    @GetMapping("title/{title}")
    public ResponseEntity<MovieDto> getByTitle(@PathVariable("title") String title) {

        return ResponseEntity.ok().body(movieService.findByTitle(title));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable("id") String id){
        movieService.deleteById(id);

        return ResponseEntity.ok().body("Movie Deleted.");
    }

    @PostMapping("/filter")
    public ResponseEntity<List<MovieDto>> filterBy(@RequestBody @Valid FilterRequest filterRequest)
    {
        return ResponseEntity.ok().body(movieService.filterBy(filterRequest));
    }


}
