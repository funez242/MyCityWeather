package com.app.mycityweatherapp.exception;

public class ServiceUnavailableException extends RuntimeException {

    public ServiceUnavailableException() {
        super("Error Interno");
    }
}