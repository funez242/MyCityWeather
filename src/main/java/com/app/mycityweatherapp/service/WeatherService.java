package com.app.mycityweatherapp.service;

import com.app.mycityweatherapp.dto.GetCityWeatherResponse;

import java.util.List;

public interface WeatherService {

    List<GetCityWeatherResponse> getWeatherByCityName(String cityName, String countryCode);

    List<List<GetCityWeatherResponse>> getHistory();
}
