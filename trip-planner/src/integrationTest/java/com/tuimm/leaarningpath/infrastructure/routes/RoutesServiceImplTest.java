package com.tuimm.leaarningpath.infrastructure.routes;

import com.tuimm.leaarningpath.domain.places.GeoCoordinate;
import com.tuimm.leaarningpath.domain.places.Place;
import com.tuimm.leaarningpath.domain.routes.Route;
import com.tuimm.leaarningpath.domain.vehicles.DrivingProfile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.http.HttpClient;
import java.util.Optional;

class RoutesServiceImplTest {
    private final String baseUri = Optional.ofNullable(System.getenv("DIRECTIONS_SERVICE_BASE_URI"))
            .orElse("http://localhost:8080/directions-service");
    private final String apiKey = Optional.ofNullable(System.getenv("API_KEY"))
            .orElse("12345678");
    private RoutesServiceImpl routesService;

    @BeforeEach
    void setUp() {
        routesService = new RoutesServiceImpl(
                HttpClient.newBuilder().build(),
                baseUri,
                apiKey);
    }

    @Test
    void getRouteFromRomeToMilanByCar_shouldReturnExpectedRoute() {
        assertsExpectedResponseMatchesActualResponse(Route.builder()
                .from(Place.create("Rome", GeoCoordinate.of(41.878243, 12.52809)))
                .to(Place.create("Milan", GeoCoordinate.of(45.473702, 9.170685)))
                .distanceInKilometers(588.5171)
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .build());
    }

    @Test
    void getRouteFromRomeToMilanByBike_shouldReturnExpectedRoute() {
        assertsExpectedResponseMatchesActualResponse(Route.builder()
                .from(Place.create("Rome", GeoCoordinate.of(41.878243, 12.52809)))
                .to(Place.create("Milan", GeoCoordinate.of(45.473702, 9.170685)))
                .distanceInKilometers(754.4997)
                .drivingProfile(DrivingProfile.BIKE_PROFILE)
                .build());
    }

    @Test
    void getRouteFromRomeToMilanByPullman_shouldReturnExpectedRoute() {
        assertsExpectedResponseMatchesActualResponse(Route.builder()
                .from(Place.create("Rome", GeoCoordinate.of(41.878243, 12.52809)))
                .to(Place.create("Milan", GeoCoordinate.of(45.473702, 9.170685)))
                .distanceInKilometers(582.9502)
                .drivingProfile(DrivingProfile.HGV_PROFILE)
                .build());
    }

    private void assertsExpectedResponseMatchesActualResponse(Route expectedRoute) {
        Route actualRoute = routesService.getRoute(
                expectedRoute.getFrom(),
                expectedRoute.getTo(),
                expectedRoute.getDrivingProfile());
        Assertions.assertEquals(expectedRoute, actualRoute);
    }
}
