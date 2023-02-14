package com.tuimm.learningpath.domain.trips;

import com.tuimm.learningpath.domain.places.Place;
import com.tuimm.learningpath.domain.routes.Route;
import com.tuimm.learningpath.domain.vehicles.DrivingPolicy;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.weatherconditions.WeatherCondition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;

import static org.mockito.Mockito.*;

class StagePlanTest {
    private StagePlan stagePlan;
    private Route route;
    private Vehicle vehicle;
    private final LocalDateTime startDateTime = LocalDateTime.of(2023,1,1,9,0,0);
    private final WeatherCondition weatherCondition = WeatherCondition.RAINY;
    private final int numberOfPeople = 3;

    @BeforeEach
    void setUp()
    {
        route = mock(Route.class);
        vehicle = mock(Vehicle.class);
        stagePlan = spy(StagePlan
                .builder()
                .vehicle(vehicle)
                .route(route)
                .destinationWeatherCondition(weatherCondition)
                .startDateTime(startDateTime)
                .numberOfPeople(numberOfPeople)
                .build());
    }

    @Test
    void getPrice_shouldReturnExpectedPrice() {
        int expectedDurationInSeconds = 173000;
        int expectedDurationInDays = 2;
        double expectedDistanceInKilometers = 10;
        double expectedPrice = 15;

        when(stagePlan.getDuration()).thenReturn(Duration.ofSeconds(expectedDurationInSeconds));
        when(route.getDistanceInKilometers()).thenReturn(expectedDistanceInKilometers);
        when(vehicle.computePrice(expectedDurationInDays, expectedDistanceInKilometers))
                .thenReturn(expectedPrice);

        clearInvocations(stagePlan, route);
        double actualPrice = stagePlan.getPrice();

        Assertions.assertEquals(expectedPrice, actualPrice, 0);
        verify(stagePlan, times(1)).getDuration();
        verify(route, times(1)).getDistanceInKilometers();
    }

    @Test
    void getEmissions_shouldReturnExpectedValue() {
        double expectedEmissionsPerKilometer = 0.5;
        double expectedDistanceInKilometer = 10;
        double expectedEmissions = 5;

        when(vehicle.getEmissions()).thenReturn(expectedEmissionsPerKilometer);
        when(route.getDistanceInKilometers()).thenReturn(expectedDistanceInKilometer);

        Assertions.assertEquals(expectedEmissions,
                stagePlan.getEmissions(),
                0);

        verify(vehicle, times(1)).getEmissions();
        verify(route, times(1)).getDistanceInKilometers();
    }

    @Test
    void getRequiredStops_shouldReturnExpectedValue() {
        double expectedAutonomy = 1.9;
        double distanceInKilometers = 5;
        int expectedRequiredStops = 2;
        when(vehicle.getAutonomy()).thenReturn(expectedAutonomy);
        when(route.getDistanceInKilometers()).thenReturn(distanceInKilometers);
        Assertions.assertEquals(expectedRequiredStops, stagePlan.getRequiredStops());
        verify(vehicle, times(1)).getAutonomy();
        verify(route, times(1)).getDistanceInKilometers();
    }

    @Test
    void getDuration_shouldReturnExpectedValue() {
        double expectedAverageSpeed = 60;
        double expectedDistanceInKilometers = 100;
        int expectedStopTimeInSeconds = 300;
        int expectedRequiredStops = 2;
        Duration expectedDuration = Duration.ofSeconds(6600);

        when(vehicle.computeAverageSpeedForPassengersAmount(numberOfPeople))
                .thenReturn(expectedAverageSpeed);
        when(vehicle.getStopTimeInSeconds())
                .thenReturn(expectedStopTimeInSeconds);
        when(route.getDistanceInKilometers())
                .thenReturn(expectedDistanceInKilometers);
        when(stagePlan.getRequiredStops())
                .thenReturn(expectedRequiredStops);

        clearInvocations(stagePlan, vehicle, route);
        Assertions.assertEquals(expectedDuration, stagePlan.getDuration());

        verify(vehicle, times(1))
                .computeAverageSpeedForPassengersAmount(numberOfPeople);
        verify(vehicle, times(1))
                .getStopTimeInSeconds();
        verify(route, times(1))
                .getDistanceInKilometers();
        verify(stagePlan, times(1)).getRequiredStops();
    }

    @Test
    void getArrivalTime_shouldReturnExpectedValue() {
        Duration expectedDuration = Duration.ofSeconds(3600);
        LocalDateTime expectedArrivalDateTime = LocalDateTime.of(2023,1,1,10,0,0);
        when(stagePlan.getDuration()).thenReturn(expectedDuration);
        clearInvocations(stagePlan);
        Assertions.assertEquals(expectedArrivalDateTime, stagePlan.getArrivalDateTime());
        verify(stagePlan, times(1)).getDuration();
    }

    @Test
    void warnForDestinationWeatherCondition_shouldReturnTrue_whenTheDestinationWeatherRequiresCoverageAndTheVehicleHasNotCoverage() {
        DrivingPolicy policy = DrivingPolicy.builder()
                .suitableForBadWeather(false)
                .build();
        when(vehicle.getDrivingPolicy()).thenReturn(policy);
        Assertions.assertTrue(stagePlan.warnForWeatherCondition());
        verify(vehicle, times(1)).getDrivingPolicy();
    }

    @Test
    void warnForDestinationWeatherCondition_shouldReturnFalse_whenTheDestinationWeatherRequiresCoverageAndTheVehicleHasCoverage() {
        DrivingPolicy policy = DrivingPolicy.builder()
                .suitableForBadWeather(true)
                .build();
        when(vehicle.getDrivingPolicy()).thenReturn(policy);
        Assertions.assertFalse(stagePlan.warnForWeatherCondition());
        verify(vehicle, times(1)).getDrivingPolicy();
    }

    @Test
    void warnForDestinationWeatherCondition_shouldReturnFalse_whenTheDestinationWeatherDoesNotRequireCoverage() {
        when(stagePlan.getDestinationWeatherCondition()).thenReturn(WeatherCondition.SUNNY);
        Assertions.assertFalse(stagePlan.warnForWeatherCondition());
    }

    @Test
    void toString_shouldReturnExpectedResult() {
        String expectedFrom = "from";
        String expectedTo = "to";
        Place placeFrom = mock(Place.class);
        Place placeTo = mock(Place.class);

        Duration expectedDuration = Duration.ofSeconds(10);
        LocalDateTime expectedArrivalDateTime =
                LocalDateTime.of(2023,1,1,9,0,0);
        double expectedPrice = 10;
        double expectedEmissions = 15;
        double expectedDistanceInKilometers = 20;
        boolean expectedWarnForWeatherCondition = true;

        when(placeFrom.getName()).thenReturn(expectedFrom);
        when(placeTo.getName()).thenReturn(expectedTo);
        when(route.getFrom()).thenReturn(placeFrom);
        when(route.getTo()).thenReturn(placeTo);
        when(route.getDistanceInKilometers()).thenReturn(expectedDistanceInKilometers);

        when(stagePlan.getDuration()).thenReturn(expectedDuration);
        when(stagePlan.getArrivalDateTime()).thenReturn(expectedArrivalDateTime);
        when(stagePlan.getPrice()).thenReturn(expectedPrice);
        when(stagePlan.getEmissions()).thenReturn(expectedEmissions);
        when(vehicle.getDrivingPolicy()).thenReturn(DrivingPolicy.builder().build());
        when(stagePlan.warnForWeatherCondition()).thenReturn(expectedWarnForWeatherCondition);

        String expectedString = String.format("StagePlan:%s", System.lineSeparator()) +
                String.format(" from: %s%s", expectedFrom, System.lineSeparator()) +
                String.format(" to: %s%s", expectedTo, System.lineSeparator()) +
                String.format(" distance: %f km%s", expectedDistanceInKilometers, System.lineSeparator()) +
                String.format(" duration: %d s%s", expectedDuration.toSeconds(), System.lineSeparator()) +
                String.format(" arrivalDateTime: 2023-01-01 09:00:00%s", System.lineSeparator()) +
                String.format(" totalPrice: %f EUR%s", expectedPrice, System.lineSeparator()) +
                String.format(" totalEmissions: %f CO2%s", expectedEmissions, System.lineSeparator()) +
                String.format(" destinationWeatherCondition: %s%s", weatherCondition, System.lineSeparator()) +
                String.format(" warnForWeatherCondition: %s%s", expectedWarnForWeatherCondition, System.lineSeparator()) +
                String.format(" vehicle: %s", vehicle);

        Assertions.assertEquals(expectedString, stagePlan.toString());
    }
}
