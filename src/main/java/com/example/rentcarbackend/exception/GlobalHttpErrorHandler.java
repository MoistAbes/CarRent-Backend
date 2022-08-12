package com.example.rentcarbackend.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class GlobalHttpErrorHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UsernameIsTakenException.class)
    public ResponseEntity<Object> handleUsernameTakenException(UsernameIsTakenException exception){
        return new ResponseEntity<>("Username is taken", HttpStatus.BAD_REQUEST);
    }
}
