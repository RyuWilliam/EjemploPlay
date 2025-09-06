package co.edu.uptc.ejemplo.play.domain.repository;

import co.edu.uptc.ejemplo.play.domain.dto.MovieDTO;
import co.edu.uptc.ejemplo.play.domain.dto.UpdateMovieDTO;

import java.util.List;

public interface MovieRepository {

    List<MovieDTO> getAll();
    MovieDTO getForId(long id);
    MovieDTO save(MovieDTO movieDTO);
    MovieDTO update(long id, UpdateMovieDTO updateMovieDTO);
    void delete(long id);

}
