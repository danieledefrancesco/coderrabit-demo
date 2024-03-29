package com.tuimm.learningpath.routes;

import com.tuimm.learningpath.places.GeoCoordinate;
import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.vehicles.DrivingProfile;
import com.tuimm.learningpath.IntegrationTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@IntegrationTest
class RoutesServiceImplTest {
    @Autowired
    private RoutesServiceImpl routesService;

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
