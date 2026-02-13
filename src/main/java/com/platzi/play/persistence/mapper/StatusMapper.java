package com.platzi.play.persistence.mapper;

import com.platzi.play.domain.Status;
import org.mapstruct.Named;

public class StatusMapper {

    @Named("stringToStatus")
    public static Status stringToStatus(String status) {

        if ( status == null ) return null;

        return switch (status) {
            case "D" -> Status.AVAILABLE;
            case "N" -> Status.UNAVAILABLE;
            default -> null;
        };

    }

    @Named("statusToString")
    public static String statusToString(Status status) {

        if ( status == null ) return null;

        return switch (status) {
            case AVAILABLE -> "D";
            case UNAVAILABLE -> "N";
            default -> null;
        };

    }

}
