package com.mohsen.springtunes.aop;

import com.mohsen.springtunes.exception.DuplicateArtistError;
import com.mohsen.springtunes.exception.DuplicateArtistException;
import com.mohsen.springtunes.exception.DuplicateSongError;
import com.mohsen.springtunes.exception.DuplicateSongException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalTime;

@ControllerAdvice
public class MainControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<DuplicateSongError> handleException(DuplicateSongException exception) {
        DuplicateSongError error = new DuplicateSongError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

    @ExceptionHandler
    public ResponseEntity<DuplicateArtistError> handleException(DuplicateArtistException exception) {
        DuplicateArtistError error = new DuplicateArtistError();
        error.setMessage(exception.getMessage());
        error.setStatus(HttpStatus.CONFLICT.value());
        error.setTimeStamp(LocalTime.now());
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
