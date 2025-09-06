package co.edu.uptc.ejemplo.play.domain.dto;

import co.edu.uptc.ejemplo.play.domain.Genre;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;

import java.time.LocalDate;

public record MovieDTO(

        @NotBlank(message = "La película debe tener un título")
        String title,


        @Max(value = 600, message = "La pelicula no puede durar más de 10hrs")
        @Positive(message = "Debe tener duración positiva en minutos")
        Integer duration,


        Genre genre,
        boolean state,
        LocalDate releaseDate,
        Double rating
) {
}
