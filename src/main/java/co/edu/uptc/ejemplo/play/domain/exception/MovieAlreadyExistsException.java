package co.edu.uptc.ejemplo.play.domain.exception;

public class MovieAlreadyExistsException extends RuntimeException{

    public MovieAlreadyExistsException(String movieTitle){
        super("La película con el nombre "+ movieTitle + " ya existe");

    }
}
