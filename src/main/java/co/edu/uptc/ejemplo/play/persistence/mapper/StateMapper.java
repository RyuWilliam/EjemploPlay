package co.edu.uptc.ejemplo.play.persistence.mapper;

import org.mapstruct.Named;

public class StateMapper {

    @Named("stateToBoolean")
    public static boolean stateToBoolean(String state){
        return state.equals("D");
    }

    @Named("booleanToState")
    public static String booleanToState(boolean state) {
        return state ? "D" : "N";
    }
}
