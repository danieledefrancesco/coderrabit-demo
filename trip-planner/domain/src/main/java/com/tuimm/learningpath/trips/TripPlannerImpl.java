package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.Driver;
import com.tuimm.learningpath.drivers.DriversRepository;
import com.tuimm.learningpath.exceptions.NoSuitableDriverException;
import com.tuimm.learningpath.exceptions.NoSuitableVehicleException;
import com.tuimm.learningpath.places.PlacesService;
import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.routes.Route;
import com.tuimm.learningpath.routes.RoutesService;
import com.tuimm.learningpath.vehicles.Garage;
import com.tuimm.learningpath.vehicles.Vehicle;
import com.tuimm.learningpath.weatherconditions.WeatherCondition;
import com.tuimm.learningpath.weatherconditions.WeatherConditionsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@Component
@RequiredArgsConstructor
public class TripPlannerImpl implements TripPlanner {
    @NonNull
    private final Garage garage;
    @NonNull
    private final WeatherConditionsService weatherConditionsService;
    @NonNull
    private final PlacesService placesService;
    @NonNull
    private final RoutesService routesService;
    @NonNull
    private final DriversRepository driversRepository;
    @NonNull
    private final Supplier<StagePlan.StagePlanBuilder> stagePlanBuilderSupplier;

    @Override
    public TripPlan planTrip(TripDefinition tripDefinition) {
        Collection<Vehicle> vehicles = ensureSuitableVehicles(tripDefinition);

        List<StagePlan> stages = new ArrayList<>(tripDefinition.getStages().size());
        LocalDateTime start = tripDefinition.getStart();

        for (StageDefinition stageDefinition : tripDefinition.getStages()) {
            StagePlan stagePlan = computePreferredStage(start, vehicles, stageDefinition, tripDefinition);
            start = stagePlan.getArrivalDateTime();
            stages.add(stagePlan);
        }
        return TripPlan.builder()
                .stages(stages)
                .build();
    }

    private Collection<Vehicle> ensureSuitableVehicles(TripDefinition tripDefinition) {
        Collection<Vehicle> vehicles = garage.getSuitableVehicles(tripDefinition.getNumberOfPeople());

        if (vehicles.isEmpty()) {
            throw new NoSuitableVehicleException();
        }
        return vehicles;
    }

    private StagePlan computePreferredStage(LocalDateTime start,
                                            Collection<Vehicle> vehicles,
                                            StageDefinition stageDefinition,
                                            TripDefinition tripDefinition) {
        Place from = placesService.fromName(stageDefinition.getFrom());
        Place to = placesService.fromName(stageDefinition.getTo());
        WeatherCondition weatherCondition = weatherConditionsService.getWeatherCondition();

        return vehicles.stream()
                .map(vehicle -> createStagePlan(start, vehicle, from, to, weatherCondition, tripDefinition.getNumberOfPeople()))
                .min(stageDefinition.getPreferredPlanPolicy())
                .orElseThrow();
    }

    private StagePlan createStagePlan(LocalDateTime start,
                                      Vehicle vehicle,
                                      Place from,
                                      Place to,
                                      WeatherCondition weatherCondition,
                                      int numberOfPeople) {
        Route route = routesService.getRoute(from, to, vehicle.getDrivingPolicy().getDrivingProfile());
        Driver driver = getDriverForVehicle(vehicle, start.toLocalDate());
        return stagePlanBuilderSupplier.get()
                .startDateTime(start)
                .destinationWeatherCondition(weatherCondition)
                .route(route)
                .vehicle(vehicle)
                .numberOfPeople(numberOfPeople)
                .driver(driver)
                .build();
    }

    private Driver getDriverForVehicle(Vehicle vehicle, LocalDate start) {
        Collection<Driver> candidateDrivers = vehicle.getDrivingPolicy().requiresDrivingLicense() ?
                driversRepository.findByMinimumAgeAndValidLicense(vehicle.getDrivingPolicy().getMinimumDrivingAge(), start) :
                driversRepository.findAll();

        if(candidateDrivers.isEmpty()) {
            throw new NoSuitableDriverException();
        }

        return candidateDrivers.stream().findFirst().orElseThrow(UnsupportedOperationException::new);
    }
}
