package com.tuimm.leaarningpath.infrastructure.weatherconditions;

import com.tuimm.leaarningpath.domain.weatherconditions.WeatherCondition;
import com.tuimm.leaarningpath.domain.weatherconditions.WeatherConditionsService;
import com.tuimm.leaarningpath.infrastructure.HttpClientUtils;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.Formatter;
import java.util.Locale;
@RequiredArgsConstructor
public class WeatherConditionServiceImpl implements WeatherConditionsService {
    private final HttpClient httpClient;
    private final String baseUri;

    @Override
    public WeatherCondition getWeatherCondition(double latitude, double longitude) {
        try (Formatter formatter = new Formatter(Locale.US)) {
            URI uri = URI.create(formatter.format("%s/weather?lat=%f&lon=%f",
                    baseUri,
                    latitude,
                    longitude).toString());
            WeatherConditionResponse weatherConditionResponse = HttpClientUtils.executeGet(httpClient, uri, WeatherConditionResponse.class);
            return WeatherCondition.valueOf(weatherConditionResponse.getWeatherCondition());
        }
    }
}
