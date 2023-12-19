package com.app.mycityweatherapp.service;

import com.app.mycityweatherapp.dto.CityDetails;

import com.app.mycityweatherapp.dto.GetCityWeatherResponse;
import com.app.mycityweatherapp.dto.OpenWeatherAPIResponse;
import com.app.mycityweatherapp.util.Constants;
import org.springframework.boot.web.client.RestTemplateBuilder;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    public WeatherService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }


    public List<GetCityWeatherResponse> getWeatherByCityName(String cityName, String countryCode){
        String geoURL = getOpenWeatherGeoUrl(cityName,countryCode);

        CityDetails[] geoResponse = this.restTemplate.getForObject(geoURL,CityDetails[].class);
        System.out.println("Ejecuccion geo correcta");
        List<CityDetails> cityDetailsList= Arrays.asList(geoResponse);

        List<GetCityWeatherResponse> getCityWeatherResponses = new ArrayList<>();

        for(CityDetails cityDetails:cityDetailsList) {
            String latitude = cityDetails.getLatitude();
            String longitude = cityDetails.getLongitude();
            String state = cityDetails.getState();
            String country = cityDetails.getCountry();
            String weatherURL = getOpenWeatherUrl(latitude, longitude);
            OpenWeatherAPIResponse weatherAPIResponse = this.restTemplate.getForObject(weatherURL, OpenWeatherAPIResponse.class);
            GetCityWeatherResponse getWeatherResponse = new GetCityWeatherResponse();
            getWeatherResponse.setWeather(weatherAPIResponse.getWeather());
            getWeatherResponse.setMain(weatherAPIResponse.getMain());
            getWeatherResponse.setCountry(country);
            getWeatherResponse.setState(state);
            getCityWeatherResponses.add(getWeatherResponse);
        }
        return getCityWeatherResponses;
    }


    private String getOpenWeatherGeoUrl(String cityName, String countryCode) {
        cityName = cityName+","+countryCode;
        return Constants.OPENWEATHER_API_GEO_BASE_URL +
                Constants.OPENWEATHER_API_CITYNAME +
                cityName +
                Constants.OPENWEATHER_API_LIMIT+
                Constants.OPENWEATHER_API_KEY +
                Constants.API_KEY;
    }

    private String getOpenWeatherUrl(String latitude, String longitude) {
        return Constants.OPENWEATHER_API_BASE_URL +
                Constants.OPENWEATHER_API_LAT + latitude +
                Constants.OPENWEATHER_API_LON + longitude +
                Constants.OPENWEATHER_API_LANG +
                Constants.OPENWEATHER_API_UNITS +
                Constants.OPENWEATHER_API_KEY +
                Constants.API_KEY;
    }

}
