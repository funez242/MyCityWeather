package com.app.mycityweatherapp.controller;


import com.app.mycityweatherapp.dto.MessageResponse;
import com.app.mycityweatherapp.exception.CityNotFoundException;
import com.app.mycityweatherapp.exception.ServiceUnavailableException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(CityNotFoundException.class)
    public ResponseEntity<MessageResponse> handleCityNotFoundException(CityNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(ServiceUnavailableException.class)
    public ResponseEntity<MessageResponse> handleCityNotFoundException(ServiceUnavailableException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MessageResponse(e.getMessage()));
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<MessageResponse> handleException(RuntimeException exception) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
                new MessageResponse("Error Interno, intente más tarde"));
    }
}