package com.app.mycityweatherapp.config;

import com.app.mycityweatherapp.datacache.CacheStore;
import com.app.mycityweatherapp.dto.GetCityWeatherResponse;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.concurrent.TimeUnit;

@Configuration
public class CacheStoreBeans {

    @Bean
    public CacheStore<List<GetCityWeatherResponse>> weatherCache() {
        return new CacheStore<>(10, TimeUnit.MINUTES);
    }

}