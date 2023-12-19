package com.app.mycityweatherapp.dto;

import lombok.Data;

import java.util.List;

@Data
public class GetCityWeatherResponse {

    private List<Weather> weather;

    private Main main;

    private String country;

    private String state;
}
