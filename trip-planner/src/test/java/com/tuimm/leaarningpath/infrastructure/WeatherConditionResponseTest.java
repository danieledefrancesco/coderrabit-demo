package com.tuimm.leaarningpath.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuimm.leaarningpath.infrastructure.weatherconditions.WeatherConditionResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class WeatherConditionResponseTest {
    @Test
    void weatherCondition_shouldBeDeserializedCorrectly() throws Exception {
        String weatherCondition = "sunny";
        String json = String.format("{ \"weather_condition\": \"%s\" }", weatherCondition);
        ObjectMapper mapper = new ObjectMapper();
        WeatherConditionResponse weatherConditionResponse = mapper.readValue(json, WeatherConditionResponse.class);
        Assertions.assertEquals(weatherCondition, weatherConditionResponse.getWeatherCondition());
    }
}
