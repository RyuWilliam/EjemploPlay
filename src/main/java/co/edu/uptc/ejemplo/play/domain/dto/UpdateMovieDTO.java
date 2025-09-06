package co.edu.uptc.ejemplo.play.domain.dto;

import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record UpdateMovieDTO(

        @NotBlank(message = "El título es obligatorio")
        String title,

        @PastOrPresent(message = "La fecha de lanzamiento no puede ser futura")
        LocalDate releaseDate,


        @Min(value = 0, message = "El rating no puede ser menor a cero")
        @Max(value = 5, message = "El rating no puede ser mayor a 5")
        Double rating
) {
}
