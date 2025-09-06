package co.edu.uptc.ejemplo.play.web.exception;


import co.edu.uptc.ejemplo.play.domain.Genre;
import co.edu.uptc.ejemplo.play.domain.exception.MovieAlreadyExistsException;
import co.edu.uptc.ejemplo.play.domain.exception.MovieDoesntExistsException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler (MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleAlreadyExistsException(MovieAlreadyExistsException ex){
        Error error = new Error("Movie already exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);

    }

    @ExceptionHandler(MovieDoesntExistsException.class)
    public ResponseEntity<Error> handleException(MovieDoesntExistsException ex){
        Error error = new Error("Movie doesnt exists", ex.getMessage());
        return ResponseEntity.badRequest().body(error);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleException(MethodArgumentNotValidException ex){
        List<Error> errors = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> {
            errors.add(new Error(error.getField(),error.getDefaultMessage()));
        });
        return ResponseEntity.badRequest().body(errors);
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<Error> handleException(HttpMessageNotReadableException e) {
        String type = "format-validation-error";
        String message = "Ha ocurrido un error en el formato de los datos enviados.";

        if (e.getCause() instanceof InvalidFormatException cause && cause.getTargetType() == Genre.class) {
            type = "genre-not-found";
            message = "El género enviado no es válido. Por favor, verifique los valores permitidos.";
        }

        Error error = new Error(type, message);
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleException (Exception ex){
        Error error = new Error("Error desconocido", ex.getMessage());
        return ResponseEntity.internalServerError().body(error);
    }
}
