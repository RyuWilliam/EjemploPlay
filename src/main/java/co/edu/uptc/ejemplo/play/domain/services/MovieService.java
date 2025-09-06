package co.edu.uptc.ejemplo.play.domain.services;


import co.edu.uptc.ejemplo.play.domain.dto.MovieDTO;
import co.edu.uptc.ejemplo.play.domain.dto.UpdateMovieDTO;
import co.edu.uptc.ejemplo.play.domain.repository.MovieRepository;
import dev.langchain4j.agent.tool.Tool;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Tool("Busca todas las películas que están en la plataforma")
    public List<MovieDTO> getAll(){

        return movieRepository.getAll();
    }
    public MovieDTO getForId(long id){
        return movieRepository.getForId(id);
    }
    public MovieDTO addMovie(MovieDTO movieDTO){
        return movieRepository.save(movieDTO);
    }
    public MovieDTO update(long id, UpdateMovieDTO updateMovieDTO){
        return movieRepository.update(id,updateMovieDTO);
    }
    public void delete(long id){
        movieRepository.delete(id);
    }

}
