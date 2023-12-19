package com.app.mycityweatherapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDetails implements Serializable {
    @JsonProperty("lat")
    private String latitude;

    @JsonProperty("lon")
    private String longitude;

    private String country;

    private String state;
}