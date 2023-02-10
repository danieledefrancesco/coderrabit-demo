package com.tuimm.learningpath.infrastructure.weatherconditions;

import com.tuimm.learningpath.domain.weatherconditions.WeatherCondition;
import com.tuimm.learningpath.domain.weatherconditions.WeatherConditionsService;
import com.tuimm.learningpath.infrastructure.HttpClientUtils;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;

@RequiredArgsConstructor
public class WeatherConditionServiceImpl implements WeatherConditionsService {
    private final HttpClient httpClient;
    private final String baseUri;

    @Override
    public WeatherCondition getWeatherCondition() {
        URI uri = URI.create(String.format("%s/random", baseUri));
        WeatherConditionResponse weatherConditionResponse = HttpClientUtils.executeGet(httpClient, uri, WeatherConditionResponse.class);
        String condition = weatherConditionResponse.getCondition().toUpperCase().replace(" ", "_");
        return WeatherCondition.valueOf(condition);
    }
}
