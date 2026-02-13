package com.platzi.play.domain.dto;

import com.platzi.play.domain.Genre;
import com.platzi.play.domain.Status;

import java.time.LocalDate;

public record MovieDto(
        String title,
        Integer duration,
        Genre genre,
        LocalDate releaseDate,
        Double raiting,
        Status status
) {
}
