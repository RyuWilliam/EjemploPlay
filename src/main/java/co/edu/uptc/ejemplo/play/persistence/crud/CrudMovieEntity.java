package co.edu.uptc.ejemplo.play.persistence.crud;

import co.edu.uptc.ejemplo.play.persistence.entity.MovieEntity;
import org.springframework.data.repository.CrudRepository;

public interface CrudMovieEntity extends CrudRepository<MovieEntity,Long> {
    MovieEntity findFirstByTitle(String title);

}
