package com.platzi.play.domain.repository;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.persistence.entity.MovieEntity;

import java.util.List;

public interface MovieRepository {

    List<MovieDto> getAll();

    MovieDto getById(Long id);

    MovieDto add(MovieDto movieDto);

    MovieDto update(Long id, UpdateMovieDto updateMovieDto);
}
