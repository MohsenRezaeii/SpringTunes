package com.mohsen.springtunes.aop;

import com.mohsen.springtunes.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class MainControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<DuplicateSongError> handleDuplicateSongException(DuplicateSongException exception) {
        DuplicateSongError error = new DuplicateSongError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<DuplicateArtistError> handleDuplicateArtistException(DuplicateArtistException exception) {
        DuplicateArtistError error = new DuplicateArtistError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<GenericError> handleGenericException(Exception exception) {
        GenericError error = new GenericError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
