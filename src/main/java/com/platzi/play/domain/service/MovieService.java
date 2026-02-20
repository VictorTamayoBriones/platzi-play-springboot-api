package com.platzi.play.domain.service;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.domain.repository.MovieRepository;
import com.platzi.play.persistence.entity.MovieEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

   public MovieService(MovieRepository movieRepository) {
       this.movieRepository = movieRepository;
   }

    public List<MovieDto> getAll() {
        return this.movieRepository.getAll();
    }

    public MovieDto getById(Long id) {
       return movieRepository.getById(id);
    }

    public MovieDto add(MovieDto movieDto) { return movieRepository.add(movieDto); }

    public MovieDto update(Long id, UpdateMovieDto updateMovieDto) {
       return this.movieRepository.update(id, updateMovieDto);
    }
}
