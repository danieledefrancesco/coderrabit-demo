package com.tuimm.learningpath.domain.weatherconditions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherConditionTest {
    @Test
    void requiresCoverage_shouldReturnExpectedValue() {
        Assertions.assertFalse(WeatherCondition.PARTLY_SUNNY.isBadWeather());
        Assertions.assertFalse(WeatherCondition.CLOUDY.isBadWeather());
        Assertions.assertTrue(WeatherCondition.RAINY.isBadWeather());
        Assertions.assertTrue(WeatherCondition.SNOWY.isBadWeather());
        Assertions.assertFalse(WeatherCondition.CLOUDY.isBadWeather());
        Assertions.assertFalse(WeatherCondition.PARTLY_CLOUDY.isBadWeather());
        Assertions.assertFalse(WeatherCondition.SUNNY.isBadWeather());
        Assertions.assertFalse(WeatherCondition.WINDY.isBadWeather());
    }
}
