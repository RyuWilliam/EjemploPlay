package co.edu.uptc.ejemplo.play.persistence;

import co.edu.uptc.ejemplo.play.domain.dto.MovieDTO;
import co.edu.uptc.ejemplo.play.domain.dto.UpdateMovieDTO;
import co.edu.uptc.ejemplo.play.domain.exception.MovieAlreadyExistsException;
import co.edu.uptc.ejemplo.play.domain.exception.MovieDoesntExistsException;
import co.edu.uptc.ejemplo.play.domain.repository.MovieRepository;
import co.edu.uptc.ejemplo.play.persistence.crud.CrudMovieEntity;
import co.edu.uptc.ejemplo.play.persistence.entity.MovieEntity;
import co.edu.uptc.ejemplo.play.persistence.mapper.MovieMapper;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public class MovieEntityRepository implements MovieRepository {

    private final CrudMovieEntity crudMovieEntity;
    private final MovieMapper movieMapper;

    public MovieEntityRepository(CrudMovieEntity crudMovieEntity, MovieMapper movieMapper) {
        this.crudMovieEntity = crudMovieEntity;
        this.movieMapper = movieMapper;
    }

    @Override
    public List<MovieDTO> getAll() {
        return movieMapper.toDto(crudMovieEntity.findAll());
    }

    @Override
    public MovieDTO getForId(long id) {
        MovieEntity movieEntity = crudMovieEntity.findById(id).orElse(null);
        if (movieEntity == null){
            throw new MovieDoesntExistsException(id);
        }

        return movieMapper.toDto(movieEntity);

    }

    public MovieDTO save(MovieDTO movieDTO){

        if(crudMovieEntity.findFirstByTitle(movieDTO.title()) != null){
        throw new MovieAlreadyExistsException(movieDTO.title());
        }

        MovieEntity movieEntity = movieMapper.toEntity(movieDTO);
        movieEntity.setState("D");

    return movieMapper.toDto(crudMovieEntity.save(movieEntity));

    }

    public MovieDTO update(long id, UpdateMovieDTO updateMovieDTO){
        MovieEntity movieEntity =  crudMovieEntity.findById(id).orElse(null);
        if(movieEntity == null){
            throw new MovieDoesntExistsException(id);
        }
        movieMapper.updateEntityFromDTO(updateMovieDTO, movieEntity);
        return movieMapper.toDto(crudMovieEntity.save(movieEntity));
    }

    public void delete(long id){
        MovieEntity movieEntity = crudMovieEntity.findById(id).orElse(null);
        if(movieEntity == null){
            throw new MovieDoesntExistsException(id);
        }
        crudMovieEntity.delete(movieEntity);
    }


}
