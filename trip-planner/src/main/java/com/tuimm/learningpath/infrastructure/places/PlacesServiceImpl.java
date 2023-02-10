package com.tuimm.learningpath.infrastructure.places;

import com.tuimm.learningpath.domain.places.GeoCoordinate;
import com.tuimm.learningpath.domain.places.Place;
import com.tuimm.learningpath.domain.places.PlacesService;
import com.tuimm.learningpath.infrastructure.HttpClientUtils;
import lombok.RequiredArgsConstructor;

import java.net.URI;
import java.net.http.HttpClient;
import java.util.List;
@RequiredArgsConstructor
public class PlacesServiceImpl implements PlacesService {
    private final HttpClient httpClient;
    private final String baseUri;
    private final String apiKey;

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