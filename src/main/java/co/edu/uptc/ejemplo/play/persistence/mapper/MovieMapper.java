package co.edu.uptc.ejemplo.play.persistence.mapper;

import co.edu.uptc.ejemplo.play.domain.dto.MovieDTO;
import co.edu.uptc.ejemplo.play.domain.dto.UpdateMovieDTO;
import co.edu.uptc.ejemplo.play.persistence.entity.MovieEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = {GenreMapper.class,StateMapper.class})
public interface MovieMapper {

     @Mapping(source = "title", target = "title")
     @Mapping(source = "duration", target = "duration")
     @Mapping(source = "genre", target = "genre", qualifiedByName ="stringToGenre")
     @Mapping(source = "premiere", target = "releaseDate")
     @Mapping(source = "classification", target = "rating")
     @Mapping(source = "state", target = "state", qualifiedByName ="stateToBoolean")
     MovieDTO toDto(MovieEntity entity);

     List<MovieDTO> toDto(Iterable<MovieEntity> entities);

     @InheritInverseConfiguration
     @Mapping(source = "genre", target = "genre", qualifiedByName = "genreToString")
     @Mapping(source ="state", target = "state", qualifiedByName = "booleanToState")
     MovieEntity toEntity(MovieDTO movieDTO);

     @Mapping(source = "title", target = "title")
     @Mapping(source = "releaseDate", target = "premiere")
     @Mapping(source = "rating", target = "classification")
     void updateEntityFromDTO(UpdateMovieDTO updateMovieDTO, @MappingTarget MovieEntity movieEntity);

}
