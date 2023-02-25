package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.exceptions.NoSuitableDriverException;
import com.tuimm.learningpath.exceptions.NoSuitableVehicleException;
import com.tuimm.learningpath.places.GeoCoordinate;
import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.places.PlacesService;
import com.tuimm.learningpath.routes.Route;
import com.tuimm.learningpath.routes.RoutesService;
import com.tuimm.learningpath.vehicles.DrivingPolicy;
import com.tuimm.learningpath.vehicles.DrivingProfile;
import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import com.tuimm.learningpath.weatherconditions.WeatherCondition;
import com.tuimm.learningpath.weatherconditions.WeatherConditionsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.*;

import static org.mockito.Mockito.*;

class TripPlannerImplTest {
    private TripPlannerImpl tripPlanner;
    private Garage garage;
    private WeatherConditionsService weatherConditionsService;
    private RoutesService routesService;
    private PlacesService placesService;
    private DriversRepository driversRepository;
    private static final Place ROME = Place.create("Rome",
            GeoCoordinate.of(10, 10));
    private static final Place MILAN = Place.create("Milan",
            GeoCoordinate.of(15, 15));
    private static final Place ZURICH = Place.create("Zurich",
            GeoCoordinate.of(20, 20));

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
        driversRepository = mock(DriversRepository.class);
        tripPlanner = new TripPlannerImpl(garage,
                weatherConditionsService,
                placesService,
                routesService,
                driversRepository);
    }

    @Test
    void planTrip_shouldReturnExpectedResult_whenSuitableVehiclesThatRequiresDrivingLicenseExistAndDriverExists() {
        testPlanTripInternal(18);
    }

    @Test
    void planTrip_shouldReturnExpectedResult_whenSuitableVehiclesThatDoesNotRequireDrivingLicenseExistAndDriverExists() {
        testPlanTripInternal(0);
    }

    @Test
    void planTrip_shouldThrowNoSuitableVehicleException_whenNoSuitableVehicleExist() {
        LocalDateTime tripStart =
                LocalDateTime.of(2023, 1, 1, 9, 0, 0);
        List<StageDefinition> stageDefinitions = new LinkedList<>();
        StageDefinition firstStage = StageDefinition.builder()
                .from(ROME.getName())
                .to(MILAN.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        StageDefinition secondStage = StageDefinition.builder()
                .from(MILAN.getName())
                .to(ZURICH.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        stageDefinitions.add(firstStage);
        stageDefinitions.add(secondStage);
        int numberOfPeople = 2;
        TripDefinition tripDefinition = TripDefinition.builder()
                .start(tripStart)
                .stages(stageDefinitions)
                .numberOfPeople(2)
                .build();
        when(garage.getSuitableVehicles(numberOfPeople)).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NoSuitableVehicleException.class,
                () -> tripPlanner.planTrip(tripDefinition));
    }

    @Test
    void planTrip_shouldThrowNoSuitableDriverException_whenNoSuitableDriverExist() {
        int minimumDrivingAge = 18;
        LocalDateTime tripStart =
                LocalDateTime.of(2023, 1, 1, 9, 0, 0);
        LocalDateTime secondStageStart =
                LocalDateTime.of(2023, 1, 1, 14, 0, 0);
        List<StageDefinition> stageDefinitions = new LinkedList<>();
        StageDefinition firstStage = StageDefinition.builder()
                .from(ROME.getName())
                .to(MILAN.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        StageDefinition secondStage = StageDefinition.builder()
                .from(MILAN.getName())
                .to(ZURICH.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        stageDefinitions.add(firstStage);
        stageDefinitions.add(secondStage);
        int numberOfPeople = 2;
        TripDefinition tripDefinition = TripDefinition.builder()
                .start(tripStart)
                .stages(stageDefinitions)
                .numberOfPeople(2)
                .build();
        Vehicle suitableVehicle = mock(Vehicle.class);
        DrivingPolicy drivingPolicy = DrivingPolicy.builder()
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .minimumDrivingAge(minimumDrivingAge)
                .build();
        when(suitableVehicle.getDrivingPolicy()).thenReturn(drivingPolicy);
        List<Vehicle> suitableVehicles = Collections.singletonList(suitableVehicle);
        when(garage.getSuitableVehicles(numberOfPeople)).thenReturn(suitableVehicles);
        when(placesService.fromName(ROME.getName())).thenReturn(ROME);
        when(placesService.fromName(MILAN.getName())).thenReturn(MILAN);
        when(placesService.fromName(ZURICH.getName())).thenReturn(ZURICH);

        when(routesService.getRoute(ROME, MILAN, ROME_TO_MILAN.getDrivingProfile()))
                .thenReturn(ROME_TO_MILAN);
        when(routesService.getRoute(MILAN, ZURICH, MILAN_TO_ZURICH.getDrivingProfile()))
                .thenReturn(MILAN_TO_ZURICH);

        when(weatherConditionsService.getWeatherCondition())
                .thenReturn(WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY);
        when(driversRepository.findByMinimumAgeAndValidLicense(minimumDrivingAge, tripStart.toLocalDate())).thenReturn(Collections.emptyList());
        Assertions.assertThrows(NoSuitableDriverException.class,
                () -> tripPlanner.planTrip(tripDefinition));
    }

    private void testPlanTripInternal(int minimumDrivingAge) {
        LocalDateTime tripStart =
                LocalDateTime.of(2023, 1, 1, 9, 0, 0);
        LocalDateTime secondStageStart =
                LocalDateTime.of(2023, 1, 1, 14, 0, 0);
        List<StageDefinition> stageDefinitions = new LinkedList<>();
        StageDefinition firstStage = StageDefinition.builder()
                .from(ROME.getName())
                .to(MILAN.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        StageDefinition secondStage = StageDefinition.builder()
                .from(MILAN.getName())
                .to(ZURICH.getName())
                .preferredPlanPolicy(mock(Comparator.class))
                .build();
        stageDefinitions.add(firstStage);
        stageDefinitions.add(secondStage);
        int numberOfPeople = 2;
        TripDefinition tripDefinition = TripDefinition.builder()
                .start(tripStart)
                .stages(stageDefinitions)
                .numberOfPeople(2)
                .build();
        Vehicle suitableVehicle = mock(Vehicle.class);
        DrivingPolicy drivingPolicy = DrivingPolicy.builder()
                .drivingProfile(DrivingProfile.CAR_PROFILE)
                .minimumDrivingAge(minimumDrivingAge)
                .build();
        when(suitableVehicle.getDrivingPolicy()).thenReturn(drivingPolicy);
        when(suitableVehicle.isAvailableFor(any())).thenReturn(true);
        List<Vehicle> suitableVehicles = Collections.singletonList(suitableVehicle);

        Driver driver = mock(Driver.class);
        List<Driver> drivers = Collections.singletonList(driver);

        when(garage.getSuitableVehicles(numberOfPeople)).thenReturn(suitableVehicles);

        when(placesService.fromName(ROME.getName())).thenReturn(ROME);
        when(placesService.fromName(MILAN.getName())).thenReturn(MILAN);
        when(placesService.fromName(ZURICH.getName())).thenReturn(ZURICH);

        when(routesService.getRoute(ROME, MILAN, ROME_TO_MILAN.getDrivingProfile()))
                .thenReturn(ROME_TO_MILAN);
        when(routesService.getRoute(MILAN, ZURICH, MILAN_TO_ZURICH.getDrivingProfile()))
                .thenReturn(MILAN_TO_ZURICH);

        when(weatherConditionsService.getWeatherCondition())
                .thenReturn(WeatherCondition.CLOUDY, WeatherCondition.PARTLY_CLOUDY);

        when(driversRepository.findByMinimumAgeAndValidLicense(eq(minimumDrivingAge), any())).thenReturn(drivers);
        when(driversRepository.findAll()).thenReturn(drivers);

        TripPlan actualTripPlan = tripPlanner.planTrip(tripDefinition);

        Assertions.assertEquals(2, actualTripPlan.getStages().size());
        Assertions.assertEquals(tripStart, actualTripPlan.getStages().get(0).getStartDateTime());
        Assertions.assertEquals(actualTripPlan.getStages().get(0).getArrivalDateTime(), actualTripPlan.getStages().get(1).getStartDateTime());
        Assertions.assertEquals(numberOfPeople, actualTripPlan.getStages().get(0).getNumberOfPeople());
        Assertions.assertEquals(numberOfPeople, actualTripPlan.getStages().get(1).getNumberOfPeople());
        Assertions.assertEquals(WeatherCondition.CLOUDY, actualTripPlan.getStages().get(0).getDestinationWeatherCondition());
        Assertions.assertEquals(WeatherCondition.PARTLY_CLOUDY, actualTripPlan.getStages().get(1).getDestinationWeatherCondition());
        Assertions.assertEquals(ROME_TO_MILAN, actualTripPlan.getStages().get(0).getRoute());
        Assertions.assertEquals(MILAN_TO_ZURICH, actualTripPlan.getStages().get(1).getRoute());
        Assertions.assertEquals(suitableVehicle, actualTripPlan.getStages().get(0).getVehicle());
        Assertions.assertEquals(suitableVehicle, actualTripPlan.getStages().get(1).getVehicle());
        Assertions.assertEquals(driver, actualTripPlan.getStages().get(0).getDriver());
        Assertions.assertEquals(driver, actualTripPlan.getStages().get(1).getDriver());
        verify(garage, times(1)).getSuitableVehicles(numberOfPeople);

        verify(suitableVehicle, atLeastOnce()).getDrivingPolicy();

        verify(placesService, times(1)).fromName(ROME.getName());
        verify(placesService, times(2)).fromName(MILAN.getName());
        verify(placesService, times(1)).fromName(ZURICH.getName());

        verify(routesService, times(1))
                .getRoute(ROME, MILAN, ROME_TO_MILAN.getDrivingProfile());
        verify(routesService, times(1))
                .getRoute(MILAN, ZURICH, MILAN_TO_ZURICH.getDrivingProfile());

        verify(weatherConditionsService, times(2))
                .getWeatherCondition();
    }
}
