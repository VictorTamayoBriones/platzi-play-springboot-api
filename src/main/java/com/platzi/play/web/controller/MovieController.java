package com.platzi.play.web.controller;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.SuggestRequestDTO;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.service.MovieService;
import com.platzi.play.domain.service.PlatziPlayAIService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
@Tag(name="Movies", description = "Operations about movies of PlatziPLay")
public class MovieController {

    public MovieService movieService;
    public PlatziPlayAIService aiService;

    public MovieController(MovieService movieService, PlatziPlayAIService aiService) {
        this.movieService = movieService;
        this.aiService = aiService;
    }

    @GetMapping
    public ResponseEntity<List<MovieDto>> getAll() {
        final List<MovieDto> allMovies =  movieService.getAll();
        return ResponseEntity.ok(allMovies);
    }

    @Operation(
            summary = "Get a movie by ID",
            description = "Get a movie by ID",
            responses = {
                    @ApiResponse(responseCode = "200", description = "Movie founded"),
                    @ApiResponse(responseCode = "400", description = "Movie did not found", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MovieDto> getById(@Parameter(description = "Identifier of a movie", example = "9") @PathVariable Long id) {
        final MovieDto movieSearched = movieService.getById(id);

        System.out.println("movieSearched " + movieSearched);

        if (movieSearched == null) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(movieSearched);

    }

    @PostMapping
    public ResponseEntity<MovieDto> add(@RequestBody @Valid MovieDto movieDto) {

        if (movieDto == null) {
            return ResponseEntity.badRequest().build();
        }

        final MovieDto movieCreated = movieService.add(movieDto);

        if ( movieCreated == null ) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(movieCreated);
    }

    @PostMapping("/suggest")
    ResponseEntity<String> generateMoviesSuggestion(@RequestBody SuggestRequestDTO suggestRequestDTO) {
        return ResponseEntity.ok(aiService.generateMovieSuggestions(suggestRequestDTO.userPreference()));
    }



    @PutMapping("/{id}")
    public ResponseEntity<MovieDto> update(@PathVariable Long id, @RequestBody UpdateMovieDto updateMovieDto) {
        return ResponseEntity.ok(movieService.update(id, updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        Boolean isMovieDeleted = movieService.delete(id);

        if ( isMovieDeleted == null ) {
            return ResponseEntity.badRequest().body("Error was executed retry later, please");
        }

        return ResponseEntity.ok("Movie deleted successfully");
    }

}
