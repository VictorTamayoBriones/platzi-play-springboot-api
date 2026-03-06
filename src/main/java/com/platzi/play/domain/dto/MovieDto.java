package com.platzi.play.domain.dto;

import com.platzi.play.domain.Genre;
import com.platzi.play.domain.Status;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record MovieDto(
        @NotBlank(message = "Title is required.")
        String title,
        @NotNull
        @Min(0)
        @Max(360)
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double raiting,
        Status status
) {
}
