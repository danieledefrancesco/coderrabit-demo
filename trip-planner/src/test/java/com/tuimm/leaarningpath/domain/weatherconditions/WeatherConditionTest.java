package com.tuimm.leaarningpath.domain.weatherconditions;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherConditionTest {
    @Test
    void requiresCoverage_shouldReturnExpectedValue() {
        Assertions.assertFalse(WeatherCondition.PARTLY_SUNNY.requiresCoverage());
        Assertions.assertFalse(WeatherCondition.CLOUDY.requiresCoverage());
        Assertions.assertTrue(WeatherCondition.RAINY.requiresCoverage());
        Assertions.assertTrue(WeatherCondition.SNOWY.requiresCoverage());
        Assertions.assertFalse(WeatherCondition.CLOUDY.requiresCoverage());
        Assertions.assertFalse(WeatherCondition.PARTLY_CLOUDY.requiresCoverage());
        Assertions.assertFalse(WeatherCondition.SUNNY.requiresCoverage());
    }
}
