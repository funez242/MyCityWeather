package com.app.mycityweatherapp.exception;

public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String cityName,String countryCode) {
        super("No se pudo encontrar la ciudad: " + (cityName!=null ? cityName+ ", ":"")
                + (countryCode!=null?countryCode:""));
    }
}