package co.edu.uptc.ejemplo.play.domain.exception;

public class MovieDoesntExistsException extends RuntimeException{

        public MovieDoesntExistsException(long id){
            super("Pelicula de id: " + id + " No existe");
        }
}
