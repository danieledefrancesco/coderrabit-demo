package com.tuimm.leaarningpath.domain.trips;

import com.tuimm.leaarningpath.domain.places.Place;
import com.tuimm.leaarningpath.domain.places.PlacesService;
import com.tuimm.leaarningpath.domain.routes.Route;
import com.tuimm.leaarningpath.domain.routes.RoutesService;
import com.tuimm.leaarningpath.domain.vehicles.Garage;
import com.tuimm.leaarningpath.domain.vehicles.Vehicle;
import com.tuimm.leaarningpath.domain.weatherconditions.WeatherCondition;
import com.tuimm.leaarningpath.domain.weatherconditions.WeatherConditionsService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Supplier;

@RequiredArgsConstructor
public class TripPlansServiceImpl implements TripPlansService {
    @NonNull
    private final Garage garage;
    @NonNull
    private final WeatherConditionsService weatherConditionsService;
    @NonNull
    private final PlacesService placesService;
    @NonNull
    private final RoutesService routesService;
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
        return TripPlan.create(stages);
    }

    private Collection<Vehicle> ensureSuitableVehicles(TripDefinition tripDefinition) {
        Collection<Vehicle> vehicles = garage.getSuitableVehicles(tripDefinition.getNumberOfPeople());

        if (vehicles.isEmpty()) {
            throw new UnsupportedOperationException("No suitable vehicles found");
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
        Route route = routesService.getRoute(from, to, vehicle.getDrivingProfile());
        return stagePlanBuilderSupplier.get()
                .startDateTime(start)
                .destinationWeatherCondition(weatherCondition)
                .route(route)
                .vehicle(vehicle)
                .numberOfPeople(numberOfPeople)
                .build();
    }
}
