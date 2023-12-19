package com.app.mycityweatherapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class Main {

    private Double temp;

    @JsonProperty("feels_like")
    private Double feelsLike;

    private Integer humidity;
}
