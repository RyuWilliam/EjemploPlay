package co.edu.uptc.ejemplo.play.persistence.mapper;

import co.edu.uptc.ejemplo.play.domain.Genre;
import org.mapstruct.Named;

public class GenreMapper {


    @Named("stringToGenre")
    public static Genre stringToGenre(String genre){

        if(genre == null) return null;


        return switch (genre.toUpperCase()){
            case "ANIMADA" -> Genre.ANIMATED;
            case "ACCION" -> Genre.ACTION;
            case "COMEDIA" -> Genre.COMEDY;
            case "DRAMA" -> Genre.DRAMA;
            case "TERROR" -> Genre.HORROR;
            case "CIENCIA_FICCION" -> Genre.SCI_FI;
            default -> null;
        };

    }
    @Named("genreToString")
    public static String genreToString(Genre genre){

        if(genre == null) return null;

        return switch (genre){
            case DRAMA -> "DRAMA";
            case ACTION -> "ACCION";
            case COMEDY -> "COMEDIA";
            case HORROR -> "TERROR";
            case SCI_FI -> "CIENCIA_FICCION";
            default -> null;
        };

    }
}
