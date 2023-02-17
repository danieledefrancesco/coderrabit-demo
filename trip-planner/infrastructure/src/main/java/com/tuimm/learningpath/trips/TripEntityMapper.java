package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.DriverEntityMapper;
import com.tuimm.learningpath.places.GeoCoordinate;
import com.tuimm.learningpath.places.Place;
import com.tuimm.learningpath.routes.Route;
import com.tuimm.learningpath.trips.dal.StagePlanEntity;
import com.tuimm.learningpath.trips.dal.TripEntity;
import com.tuimm.learningpath.vehicles.DrivingProfile;
import com.tuimm.learningpath.vehicles.VehicleEntitiesMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Mapper(uses = {DriverEntityMapper.class, VehicleEntitiesMapper.class})
public interface TripEntityMapper {
    @Mapping(target = "stages", expression = "java(mapToStages(trip))")
    TripEntity mapToTripEntity(Trip trip);

    default Set<StagePlanEntity> mapToStages(Trip trip) {
        return trip.getPlan().getStages().stream()
                .map(stage -> mapToStagePlanEntity(stage, trip.getId()))
                .collect(Collectors.toSet());
    }
    
    @Mapping(target = "tripId", source = "tripId")
    @Mapping(target = "startDateTime", source = "stagePlan.startDateTime")
    @Mapping(target = "fromName", source = "stagePlan.route.from.name")
    @Mapping(target = "fromLatitude", source = "stagePlan.route.from.geoCoordinate.latitude")
    @Mapping(target = "fromLongitude", source = "stagePlan.route.from.geoCoordinate.longitude")
    @Mapping(target = "toName", source = "stagePlan.route.to.name")
    @Mapping(target = "toLatitude", source = "stagePlan.route.to.geoCoordinate.latitude")
    @Mapping(target = "toLongitude", source = "stagePlan.route.to.geoCoordinate.longitude")
    @Mapping(target = "distanceInKilometers", source = "stagePlan.route.distanceInKilometers")
    @Mapping(target = "drivingProfile", source = "stagePlan.route.drivingProfile")
    @Mapping(target = "destinationWeatherCondition", source = "stagePlan.destinationWeatherCondition")
    @Mapping(target = "numberOfPeople", source = "stagePlan.numberOfPeople")
    @Mapping(target = "vehicle", source = "stagePlan.vehicle")
    @Mapping(target = "driver", source = "stagePlan.driver")
    StagePlanEntity mapToStagePlanEntity(StagePlan stagePlan, UUID tripId);

    @Mapping(target = "plan", source = "tripEntity")
    Trip mapTripEntityToTrip(TripEntity tripEntity);

    TripPlan mapTripEntityToTripPlan(TripEntity tripEntity);

    @Mapping(target = "route", expression = "java(mapStagePlanEntityToRoute(stagePlanEntity))")
    @Mapping(target = "vehicle", source = "stagePlanEntity.vehicle", qualifiedByName = "mapToVehicle")
    StagePlan mapStagePlanEntityToStagePlan(StagePlanEntity stagePlanEntity);


    default Route mapStagePlanEntityToRoute(StagePlanEntity stagePlanEntity) {
        return Route.builder()
                .from(Place.create(stagePlanEntity.getFromName(),
                        GeoCoordinate.of(stagePlanEntity.getFromLatitude(), stagePlanEntity.getFromLatitude())))
                .to(Place.create(stagePlanEntity.getToName(),
                        GeoCoordinate.of(stagePlanEntity.getToLatitude(), stagePlanEntity.getToLatitude())))
                .distanceInKilometers(stagePlanEntity.getDistanceInKilometers())
                .drivingProfile(DrivingProfile.valueOf(stagePlanEntity.getDrivingProfile()))
                .build();
    } 
}
