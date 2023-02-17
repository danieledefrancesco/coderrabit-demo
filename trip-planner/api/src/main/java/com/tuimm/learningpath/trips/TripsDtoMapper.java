package com.tuimm.learningpath.trips;

import com.tuimm.learningpath.drivers.DriversDtoMapper;
import com.tuimm.learningpath.routes.Route;
import com.tuimm.learningpath.trips.commands.CreateTripRequest;
import com.tuimm.learningpath.trips.dtos.*;
import com.tuimm.learningpath.trips.queries.GetAllTripsResponse;
import com.tuimm.learningpath.vehicles.VehiclesDtoMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(uses = {VehiclesDtoMapper.class, DriversDtoMapper.class})
public interface TripsDtoMapper {
    CreateTripRequest mapToCreateTripRequest(CreateTripRequestDto dto);

    @Mapping(target = "preferredPlanPolicy", source = "dto.preferredPlanPolicy.policy")
    StageDefinition mapToStageDefinition(CreateStageRequestDto dto);

    GetAllTripsResponseDto mapToGetAllTripsResponseDto(GetAllTripsResponse response);

    TripResponseDto mapToTripResponseDto(Trip trip);

    TripPlanResponseDto mapToTripPlanResponseDto(TripPlan tripPlan);

    StagePlanResponseDto mapToStagePlanResponseDto(StagePlan stagePlan);

    @Mapping(target = "from", source = "from.name")
    @Mapping(target = "to", source = "to.name")
    RouteResponseDto mapToRouteResponseDto(Route route);
}
