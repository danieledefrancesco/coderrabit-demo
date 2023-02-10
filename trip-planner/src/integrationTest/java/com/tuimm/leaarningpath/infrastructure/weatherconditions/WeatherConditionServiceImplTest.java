package com.tuimm.leaarningpath.infrastructure.weatherconditions;

import com.tuimm.leaarningpath.domain.weatherconditions.WeatherCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.Optional;

class WeatherConditionServiceImplTest {
    private final String baseUri = Optional.ofNullable(System.getenv("WEATHER_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/weather-service");
    private WeatherConditionServiceImpl weatherConditionService;

    @BeforeEach
    void setUp() {
        weatherConditionService = new WeatherConditionServiceImpl(HttpClient.newBuilder().build(), baseUri);
    }
    @Test
    void getWeather_shouldReturnExpectedValue_whenTheServiceReturns200() {
        Assertions.assertEquals(WeatherCondition.PARTLY_SUNNY,
                weatherConditionService.getWeatherCondition());
    }
}
