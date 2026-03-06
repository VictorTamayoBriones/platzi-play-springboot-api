package com.platzi.play.domain.exception;

public class MovieNoExistsException extends RuntimeException{

    public MovieNoExistsException() {
        super("The movie not exists");
    }

}
