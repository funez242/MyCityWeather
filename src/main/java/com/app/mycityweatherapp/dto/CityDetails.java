package com.app.mycityweatherapp.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class CityDetails implements Serializable {
    @JsonProperty("lat")
    String latitude;

    @JsonProperty("lon")
    String longitude;
}