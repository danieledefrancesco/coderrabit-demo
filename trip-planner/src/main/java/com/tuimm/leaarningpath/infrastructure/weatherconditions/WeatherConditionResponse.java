package com.tuimm.leaarningpath.infrastructure.weatherconditions;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherConditionResponse {
    @JsonProperty("weather_condition")
    private String weatherCondition;
}
