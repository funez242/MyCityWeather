package com.app.mycityweatherapp.controller;

import com.app.mycityweatherapp.dto.GetCityWeatherResponse;
import com.app.mycityweatherapp.dto.OpenWeatherAPIResponse;
import com.app.mycityweatherapp.service.WeatherService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/weather")
@Tag(name = "Aplicación para obtener el clima",
     description = "API que entrega las ultimas actualizaciones del clima.")
public class WeatherController {

    WeatherService weatherService;

    public WeatherController(WeatherService weatherService){
        this.weatherService = weatherService;
    }

    @GetMapping({"/{city}","/{city}/{countrycode}"})
    @Operation(description = "Obtiene los detalles del clima de una ciudad especifica",
               operationId = "getWeatherByCityName")
    public ResponseEntity<List<GetCityWeatherResponse>> getWeather(
            @Parameter(name = "city", example = "Guadalajara",required = true) @PathVariable("city") String cityName,
            @Parameter(name ="countrycode", example = "CO para Colombia, MX para México", required = false)
            @PathVariable(value = "countrycode",required = false) String countryCode){
        return new ResponseEntity<>(weatherService.getWeatherByCityName(cityName,countryCode), HttpStatus.OK);
    }

    @GetMapping("/history")
    @Operation(description = "Obtiene el historial de ciudades consultadas.")
    public ResponseEntity<String> getHistory(){
        return ResponseEntity.ok("History");
    }


}
