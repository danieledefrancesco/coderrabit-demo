package com.tuimm.learningpath.weatherconditions;

import com.tuimm.learningpath.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;


class WeatherConditionServiceImplTest extends IntegrationTest {
    @Autowired
    private WeatherConditionServiceImpl weatherConditionService;
    @Test
    void getWeather_shouldReturnExpectedValue_whenTheServiceReturns200() {
        Assertions.assertEquals(WeatherCondition.PARTLY_SUNNY,
                weatherConditionService.getWeatherCondition());
    }
}
