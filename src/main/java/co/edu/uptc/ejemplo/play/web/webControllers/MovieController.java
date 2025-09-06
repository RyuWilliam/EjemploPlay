package co.edu.uptc.ejemplo.play.web.webControllers;

import co.edu.uptc.ejemplo.play.domain.dto.MovieDTO;
import co.edu.uptc.ejemplo.play.domain.dto.UpdateMovieDTO;
import co.edu.uptc.ejemplo.play.domain.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Movies")

@Tag(name = "Movies", description = "Operations about movies in the platform")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public ResponseEntity<List<MovieDTO>> getAll(){
        return ResponseEntity.ok(movieService.getAll());
    }
    @Operation(
            summary = "Get an movie by id",
            description = "just that",
            responses = {
                    @ApiResponse(responseCode = "200", description = "movie found"),
                    @ApiResponse(responseCode = "404", description = "movie not found", content = @Content)
            }
    )
    @GetMapping("/{id}")
    public ResponseEntity<MovieDTO> getForId(@Parameter(description = "id de la película a encontrar", example = "198") @PathVariable long id){
        MovieDTO movieDTO = movieService.getForId(id);

        if(movieDTO == null){
                return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(movieDTO);
    }

    @PostMapping()
    public ResponseEntity<MovieDTO> addMovie(@RequestBody @Valid MovieDTO movieDTO){
        MovieDTO movieDTOResponse = movieService.addMovie(movieDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(movieDTOResponse);

    }
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> updateMovie(@PathVariable long id, @RequestBody @Valid UpdateMovieDTO updateMovieDto){
        return ResponseEntity.ok(movieService.update(id,updateMovieDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable long id){
        movieService.delete(id);
        return ResponseEntity.ok().build();
    }
}
