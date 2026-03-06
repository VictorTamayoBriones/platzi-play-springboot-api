package com.platzi.play.domain.exception;

public class MovieAlreadyExistsException extends RuntimeException {

    public MovieAlreadyExistsException(String title) {
        super("The movie " + title + " already exists");
    }

}
