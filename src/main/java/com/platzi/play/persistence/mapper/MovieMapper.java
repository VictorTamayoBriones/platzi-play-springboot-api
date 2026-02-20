package com.platzi.play.persistence.mapper;

import com.platzi.play.domain.dto.MovieDto;
import com.platzi.play.domain.dto.UpdateMovieDto;
import com.platzi.play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper( componentModel = "spring", uses = { GenreMapper.class, StatusMapper.class })
public interface MovieMapper {

    @Mapping( source = "titulo", target = "title")
    @Mapping( source = "duracion", target = "duration")
    @Mapping( source = "genero", target = "genre", qualifiedByName = "stringToGenre" )
    @Mapping( source = "fechaEstreno", target = "releaseDate")
    @Mapping( source = "calificacion", target = "raiting")
    @Mapping( source = "estado", target = "status", qualifiedByName = "stringToStatus")
    MovieDto toDto(MovieEntity entity);
    List<MovieDto> toDto(Iterable<MovieEntity> entities);

    @InheritInverseConfiguration
    @Mapping( source = "genre", target = "genero", qualifiedByName = "genreToString")
    @Mapping( source = "status", target = "estado", qualifiedByName = "statusToString")
    MovieEntity toEntity(MovieDto movieDto);

    @Mapping( target = "titulo", source = "title")
    @Mapping( target = "fechaEstreno", source = "releaseDate")
    @Mapping( target = "calificacion", source = "raiting")
    void updateEntityFromDto(UpdateMovieDto updateMovieDto, @MappingTarget MovieEntity entity);
}
