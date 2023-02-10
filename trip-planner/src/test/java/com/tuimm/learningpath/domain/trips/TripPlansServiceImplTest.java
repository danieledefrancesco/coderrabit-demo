package com.tuimm.learningpath.domain.trips;

import com.tuimm.learningpath.domain.places.GeoCoordinate;
import com.tuimm.learningpath.domain.places.Place;
import com.tuimm.learningpath.domain.places.PlacesService;
import com.tuimm.learningpath.domain.routes.Route;
import com.tuimm.learningpath.domain.routes.RoutesService;
import com.tuimm.learningpath.domain.vehicles.DrivingProfile;
import com.tuimm.learningpath.domain.vehicles.Garage;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.domain.weatherconditions.WeatherCondition;
import com.tuimm.learningpath.domain.weatherconditions.WeatherConditionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.*;

class TripPlansServiceImplTest {
    private TripPlansServiceImpl tripPlansService;
    private Garage garage;
    private WeatherConditionsService weatherConditionsService;
    private RoutesService routesService;
    private PlacesService placesService;
    private StagePlan.StagePlanBuilder builder;
    private static final Place ROME = Place.create("Rome",
            GeoCoordinate.of(10,10));
    private static final Place MILAN = Place.create("Milan",
            GeoCoordinate.of(15,15));
    private static final Place ZURICH = Place.create("Zurich",
            GeoCoordinate.of(20,20));

    private static final Route ROME_TO_MILAN = Route.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .distanceInKilometers(200)
            .from(ROME)
            .to(MILAN)
            .build();

    private static final Route MILAN_TO_ZURICH = Route.builder()
            .drivingProfile(DrivingProfile.CAR_PROFILE)
            .distanceInKilometers(100)
            .from(MILAN)
            .to(ZURICH)
            .build();


    @BeforeEach
    public void setUp() {
        garage = mock(Garage.class);
        weatherConditionsService = mock(WeatherConditionsService.class);
        routesService = mock(RoutesService.class);
        placesService = mock(PlacesService.class);
        builder = mock(StagePlan.StagePlanBuilder.class);
        tripPlansService = new TripPlansServiceImpl(garage,
                weatherConditionsService,
                placesService,
                routesService,
                () -> builder);
    }

    @Test
    void planTrip_shouldReturnExpectedResult_whenSuitableVehiclesExist() {
        LocalDateTime tripStart =
                LocalDateTime.of(2023, 1, 1, 9, 0, 0);
        LocalDateTime secondStageStart =
                LocalDateTime.of(2023, 1, 1, 14, 0, 0);
        List<StageDefinition> stageDefinitions = new LinkedList<>();
        StageDefinition firstStage = StageDefinition.create(ROME.getName(), MILAN.getName(), mock(Comparator.class));
        StageDefinition secondStage = StageDefinition.create(MILAN.getName(), ZURICH.getName(), mock(Comparator.class));
        stageDefinitions.add(firstStage);
        stageDefinitions.add(secondStage);
        int numberOfPeople = 2;
        TripDefinition tripDefinition = TripDefinition.create(tripStart, stageDefinitions, 2);
        Vehicle suitableVehicle = mock(Vehicle.class);
        List<Vehicle> suitableVehicles = new LinkedList<>();
        suitableVehicles.add(suitableVehicle);

        StagePlan firstStagePlan = mock(StagePlan.class);
        StagePlan secondStagePlan = mock(StagePlan.class);

        TripPlan expectedTripPlan = TripPlan.create(Arrays.asList(firstStagePlan, secondStagePlan));

        when(suitableVehicle.getDrivingProfile()).thenReturn(DrivingProfile.CAR_PROFILE);

        when(garage.getSuitableVehicles(numberOfPeople)).thenReturn(suitableVehicles);
        when(firstStagePlan.getArrivalDateTime()).thenReturn(secondStageStart);

        when(placesService.fromName(ROME.getName())).thenReturn(ROME);
        when(placesService.fromName(MILAN.getName())).thenReturn(MILAN);
        when(placesService.fromName(ZURICH.getName())).thenReturn(ZURICH);

        when(routesService.getRoute(ROME, MILAN, ROME_TO_MILAN.getDrivingProfile()))
                .thenReturn(ROME_TO_MILAN);
        when(routesService.getRoute(MILAN, ZURICH, MILAN_TO_ZURICH.getDrivingProfile()))
                .thenReturn(MILAN_TO_ZURICH);

        when(weatherConditionsService.getWeatherCondition())
                .thenReturn(WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY);

        when(builder.startDateTime(tripStart)).thenReturn(builder);
        when(builder.startDateTime(secondStageStart)).thenReturn(builder);
        when(builder.numberOfPeople(tripDefinition.getNumberOfPeople())).thenReturn(builder);
        when(builder.destinationWeatherCondition(WeatherCondition.CLOUDY)).thenReturn(builder);
        when(builder.destinationWeatherCondition(WeatherCondition.PARTLY_CLOUDY)).thenReturn(builder);
        when(builder.route(ROME_TO_MILAN)).thenReturn(builder);
        when(builder.route(MILAN_TO_ZURICH)).thenReturn(builder);
        when(builder.vehicle(suitableVehicle)).thenReturn(builder);
        when(builder.build()).thenReturn(firstStagePlan, secondStagePlan);

        TripPlan actualTripPlan = tripPlansService.planTrip(tripDefinition);

        Assertions.assertEquals(expectedTripPlan, actualTripPlan);

        verify(garage, times(1)).getSuitableVehicles(numberOfPeople);
        verify(firstStagePlan, times(1)).getArrivalDateTime();

        verify(suitableVehicle, times(2)).getDrivingProfile();

        verify(placesService, times(1)).fromName(ROME.getName());
        verify(placesService, times(2)).fromName(MILAN.getName());
        verify(placesService, times(1)).fromName(ZURICH.getName());

        verify(routesService, times(1))
                .getRoute(ROME, MILAN, ROME_TO_MILAN.getDrivingProfile());
        verify(routesService, times(1))
                .getRoute(MILAN, ZURICH, MILAN_TO_ZURICH.getDrivingProfile());

        verify(weatherConditionsService, times(2))
                .getWeatherCondition();

        verify(builder, times(1)).startDateTime(tripStart);
        verify(builder, times(1)).startDateTime(secondStageStart);
        verify(builder, times(2)).numberOfPeople(numberOfPeople);
        verify(builder, times(1)).destinationWeatherCondition(WeatherCondition.CLOUDY);
        verify(builder, times(1)).destinationWeatherCondition(WeatherCondition.PARTLY_CLOUDY);
        verify(builder, times(1)).route(ROME_TO_MILAN);
        verify(builder, times(1)).route(MILAN_TO_ZURICH);
        verify(builder, times(2)).vehicle(suitableVehicle);
        verify(builder, times(2)).build();
    }
    @Test
    void planTrip_shouldThrowIllegalOperationException_whenNoSuitableVehicleIsAvailable() {
        TripDefinition tripDefinition = TripDefinition.create(LocalDateTime.of(2023, 1, 1, 9, 0, 0),
                new LinkedList<>(),
                2);
        when(garage.getSuitableVehicles(2)).thenReturn(new LinkedList<>());
        Exception actualException = Assertions.assertThrows(UnsupportedOperationException.class,
                () -> tripPlansService.planTrip(tripDefinition));
        Assertions.assertEquals("No suitable vehicles found", actualException.getMessage());
    }
}
