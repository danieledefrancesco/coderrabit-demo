package com.tuimm.learningpath.places;

import com.tuimm.learningpath.HttpClientUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;
@Component
public class PlacesServiceImpl implements PlacesService {
    private final HttpClient httpClient;
    private final String baseUri;
    private final String apiKey;

    public PlacesServiceImpl(HttpClient httpClient,
                             @Value("${placesService.baseUri}") String baseUri,
                             @Value("${placesService.apiKey}") String apiKey) {
        this.httpClient = httpClient;
        this.baseUri = baseUri;
        this.apiKey = apiKey;
    }

    @Override
    public Place fromName(String name) {
        URI uri = URI.create(String.format("%s/geocode/search?api_key=%s&text=%s",
                baseUri,
                apiKey,
                name));
        SearchResponse searchResponse = HttpClientUtils.executeGet(httpClient, uri, SearchResponse.class);

        List<Double> coordinates = searchResponse.getFeatures().get(0).getGeometry().getCoordinates();
        return Place.create(name, GeoCoordinate.of(coordinates.get(1), coordinates.get(0)));
    }
}