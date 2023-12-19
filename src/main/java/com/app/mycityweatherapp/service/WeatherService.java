package com.app.mycityweatherapp.service;

import com.app.mycityweatherapp.datacache.CacheStore;
import com.app.mycityweatherapp.dto.CityDetails;

import com.app.mycityweatherapp.dto.GetCityWeatherResponse;
import com.app.mycityweatherapp.dto.OpenWeatherAPIResponse;
import com.app.mycityweatherapp.entities.LogsEntity;
import com.app.mycityweatherapp.exception.CityNotFoundException;
import com.app.mycityweatherapp.exception.ServiceUnavailableException;
import com.app.mycityweatherapp.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@Service
public class WeatherService {

    private final RestTemplate restTemplate;

    private LogsService logsService;

    CacheStore<List<GetCityWeatherResponse>> cacheStore;

    @Autowired
    public WeatherService(RestTemplateBuilder restTemplateBuilder, LogsService logsService,
                          CacheStore<List<GetCityWeatherResponse>> cacheStore) {
        this.restTemplate = restTemplateBuilder.build();
        this.logsService = logsService;
        this.cacheStore = cacheStore;
    }


    public List<GetCityWeatherResponse> getWeatherByCityName(String cityName, String countryCode) {
        List<GetCityWeatherResponse> getCityWeatherResponses = new ArrayList<>();

        ResponseEntity<CityDetails[]> cityDetailsEntity= callOpenWeatherGeo(cityName, countryCode);

        if (!cityDetailsEntity.getStatusCode().is2xxSuccessful() && this.cacheStore.get(cityName+countryCode).size() == 0){
            throw new ServiceUnavailableException();
        }
        if(cityDetailsEntity.getStatusCode().is2xxSuccessful() && cityDetailsEntity.getBody().length == 0){
            throw new CityNotFoundException(cityName,countryCode);
        }

        if(!cityDetailsEntity.getStatusCode().is2xxSuccessful() && this.cacheStore.get(cityName+countryCode).size() != 0) {
            return this.cacheStore.get(cityName+countryCode);
        }

        CityDetails[] geoResponse = cityDetailsEntity.getBody();

        List<CityDetails> cityDetailsList =Arrays.asList(geoResponse);

        for (CityDetails cityDetails : cityDetailsList) {
            String latitude = cityDetails.getLatitude();
            String longitude = cityDetails.getLongitude();
            String name = cityDetails.getName();
            String state = cityDetails.getState();
            String country = cityDetails.getCountry();

            ResponseEntity<OpenWeatherAPIResponse> openWeatherResponseEntity = callOpenWeatherAPI(latitude, longitude);

            if (!openWeatherResponseEntity.getStatusCode().is2xxSuccessful() && this.cacheStore.get(cityName + countryCode).size() == 0) {
                throw new ServiceUnavailableException();
            }
            if (!openWeatherResponseEntity.getStatusCode().is2xxSuccessful() && this.cacheStore.get(cityName + countryCode).size() != 0) {
                return this.cacheStore.get(cityName + countryCode);
            }

            OpenWeatherAPIResponse weatherAPIResponse = openWeatherResponseEntity.getBody();

            GetCityWeatherResponse getWeatherResponse = new GetCityWeatherResponse();
            getWeatherResponse.setWeather(weatherAPIResponse.getWeather());
            getWeatherResponse.setMain(weatherAPIResponse.getMain());
            getWeatherResponse.setCountry(country);
            getWeatherResponse.setState(state);
            getWeatherResponse.setName(name);
            getCityWeatherResponses.add(getWeatherResponse);

            String requestKey = cityName + countryCode;
            this.cacheStore.add(requestKey,getCityWeatherResponses);

            persistLogEntity(requestKey);
        }

        return getCityWeatherResponses;
    }

    public List<List<GetCityWeatherResponse>> getHistory(){
        List<LogsEntity> logsEntities = (List<LogsEntity>) this.logsService.findAllByOrderByUpdatedTimeDesc();

        List<List<GetCityWeatherResponse>> cityWeatherResponseList = new ArrayList<>();

        for (int i = 0; i < logsEntities.size() && i < 10; i++) {
            cityWeatherResponseList.add(this.cacheStore.get(logsEntities.get(i).getRequestKey()));
        }
        return cityWeatherResponseList;
    }

    private ResponseEntity<CityDetails[]> callOpenWeatherGeo(String cityName, String countryCode) {
        String geoURL = getOpenWeatherGeoUrl(cityName, countryCode);
        return this.restTemplate.getForEntity(geoURL, CityDetails[].class);
    }


    private ResponseEntity<OpenWeatherAPIResponse> callOpenWeatherAPI(String latitude, String longitude) {
        String weatherURL = getOpenWeatherUrl(latitude, longitude);
        return this.restTemplate.getForEntity(weatherURL, OpenWeatherAPIResponse.class);
    }

    private void persistLogEntity(String requestKey){
        Optional<LogsEntity> logsEntity = this.logsService.findByRequestKey(requestKey);
        System.out.println("Pasa el find:"+logsEntity.isPresent());
        if (!logsEntity.isPresent()){
            LogsEntity logsEntity1 = new LogsEntity();
            logsEntity1.setRequestKey(requestKey);
            logsEntity1.setUpdatedTime(LocalDateTime.now());
            this.logsService.saveLog(logsEntity1);
        } else {
                LogsEntity logsEntity1 = logsEntity.get();
                logsEntity1.setUpdatedTime(LocalDateTime.now());
                this.logsService.updateLog(logsEntity1,requestKey);
        }
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
