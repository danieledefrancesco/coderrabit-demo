package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.vehicles.commands.CreateBikeRequest;
import com.tuimm.learningpath.vehicles.commands.CreateCarRequest;
import com.tuimm.learningpath.vehicles.commands.CreatePullmanRequest;
import com.tuimm.learningpath.vehicles.commands.CreateScooterRequest;
import com.tuimm.learningpath.vehicles.dtos.*;
import com.tuimm.learningpath.vehicles.queries.GetVehiclesResponse;
import org.mapstruct.Mapper;

@Mapper
public interface VehiclesMapper {
    BikeResponseDto mapBike(Bike bike);
    CarResponseDto mapCar(Car car);
    PullmanResponseDto mapPullman(Pullman pullman);
    ScooterResponseDto mapScooter(Scooter scooter);
    default String map(Plate plate) {
        return plate.getValue();
    }
    default VehicleResponseDto mapVehicle(Vehicle vehicle) {
        if (vehicle instanceof Bike bike) {
            return mapBike(bike);
        }
        if (vehicle instanceof Car car) {
            return mapCar(car);
        }
        if (vehicle instanceof Pullman pullman) {
            return mapPullman(pullman);
        }
        if (vehicle instanceof Scooter scooter) {
            return mapScooter(scooter);
        }
        return null;
    }
    CreateBikeRequest mapCreateBikeRequest(CreateBikeRequestDto requestDto);
    CreateCarRequest mapCreateCarRequest(CreateCarRequestDto requestDto);
    CreatePullmanRequest mapCreatePullmanRequest(CreatePullmanRequestDto requestDto);
    CreateScooterRequest mapCreateScooterRequest(CreateScooterRequestDto requestDto);
    GetVehiclesResponseDto mapGetVehiclesResponse(GetVehiclesResponse response);
}
