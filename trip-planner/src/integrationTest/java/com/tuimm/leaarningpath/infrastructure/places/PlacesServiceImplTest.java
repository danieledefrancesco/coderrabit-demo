package com.tuimm.leaarningpath.infrastructure.places;

import com.tuimm.leaarningpath.domain.places.GeoCoordinate;
import com.tuimm.leaarningpath.domain.places.Place;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.Optional;

class PlacesServiceImplTest {
    private final String baseUri = Optional.ofNullable(System.getenv("GEOCODING_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/geocode-service");
    private final String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
            .orElse("12345678");
    private PlacesServiceImpl placesService;

    @BeforeEach
    void setUp() {
        placesService = new PlacesServiceImpl(
                HttpClient.newBuilder().build(),
                baseUri,
                apiKey);
    }

    @Test
    void fromName_shouldReturnExpectedPlace() {
        Place expectedPlace = Place.create("Rome", GeoCoordinate.of(41.878243, 12.52809));
        Place actualPlace = placesService.fromName("Rome");
        Assertions.assertEquals(expectedPlace, actualPlace);
    }
}
