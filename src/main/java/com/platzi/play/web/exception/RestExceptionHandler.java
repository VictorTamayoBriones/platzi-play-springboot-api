package com.platzi.play.web.exception;

import com.platzi.play.domain.exception.MovieAlreadyExistsException;
import com.platzi.play.domain.exception.MovieNoExistsException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(MovieAlreadyExistsException.class)
    public ResponseEntity<Error> handleMovieAlreadyExistsException(MovieAlreadyExistsException exception) {
        Error error = new Error("movie-already-exists", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MovieNoExistsException.class)
    public ResponseEntity<Error> handleMovieNoExistsExists(MovieNoExistsException exception) {
        Error error = new Error("movie-not-exists", exception.getMessage());
        return ResponseEntity.badRequest().body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<List<Error>> handleMethodArgumentNotValid(MethodArgumentNotValidException exception) {
        System.out.println("\n\n\nERR: \n\n\n");
        List<Error> errors = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach( error -> {
          errors.add(new Error(error.getField(), error.getDefaultMessage()));
        });

        return ResponseEntity.badRequest().body(errors);

    }

    //@ExceptionHandler(Exception.class)
    //public ResponseEntity<Error> handleUnknowException(Exception exception) {
    //    exception.printStackTrace();
    //    Error error = new Error("unknow-exception", exception.getMessage());
    //    return ResponseEntity.badRequest().body(error);
    //}

}
