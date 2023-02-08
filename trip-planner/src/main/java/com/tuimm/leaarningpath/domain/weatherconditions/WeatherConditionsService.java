package com.tuimm.leaarningpath.domain.weatherconditions;

public interface WeatherConditionsService {
    WeatherCondition getWeatherCondition(double latitude, double longitude);
}
