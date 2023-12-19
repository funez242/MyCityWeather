package com.app.mycityweatherapp.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Constants {

    public static String OPENWEATHER_API_GEO_BASE_URL;
    public static final String OPENWEATHER_API_CITYNAME = "?q=";
    public static final String OPENWEATHER_API_LIMIT= "&limit=3";

    public static String OPENWEATHER_API_BASE_URL;
    public static final String OPENWEATHER_API_LAT= "?lat=";
    public static final String OPENWEATHER_API_LON= "&lon=";
    public static final String OPENWEATHER_API_LANG= "&lang=es";
    public static final String OPENWEATHER_API_UNITS= "&untis=metric";

    public static final String OPENWEATHER_API_KEY = "&appid=";
    public static String API_KEY;

    @Value("${openweather.url}")
    public void setOpenWeatherApiBaseUrl(String apiUrl) {
        Constants.OPENWEATHER_API_BASE_URL = apiUrl;
    }

    @Value("${openweather.geo.url}")
    public void setOpenWeatherApiGeoBaseUrl(String apiGeoUrl) {
        Constants.OPENWEATHER_API_GEO_BASE_URL = apiGeoUrl;
    }

    @Value("${openweather.key}")
    public void setApiKey(String apiKey) {
        API_KEY = apiKey;
    }
}
