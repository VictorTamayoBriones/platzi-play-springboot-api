package com.platzi.play.web.controller;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.service.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    public MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        final List<MovieDto> allMovies =  movieService.getAll();
        return ResponseEntity.ok(allMovies);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@PathVariable Long id) {
        final MovieDto movieSearched = movieService.getById(id);

        System.out.println("movieSearched " + movieSearched);

        if (movieSearched == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movieSearched);

    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody MovieDto movieDto) {

        if (movieDto == null) {
            return ResponseEntity.badRequest().build();
        }

        final MovieDto movieCreated = movieService.add(movieDto);

        if ( movieCreated == null ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreated);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(movieService.update(id, updateMovieDto));
    }

}
