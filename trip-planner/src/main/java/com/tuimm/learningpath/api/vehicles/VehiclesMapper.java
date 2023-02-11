package com.tuimm.learningpath.api.vehicles;

import com.tuimm.learningpath.contracts.vehicles.*;
import com.tuimm.learningpath.domain.vehicles.*;
import org.mapstruct.Mapper;

@Mapper
public interface VehiclesMapper {
    BikeResponse mapBike(Bike bike);
    CarResponse mapCar(Car car);
    PullmanResponse mapPullman(Pullman pullman);
    ScooterResponse mapScooter(Scooter scooter);
    default String map(Plate plate) {
        return plate.getValue();
    }
    default VehicleResponse mapVehicle(Vehicle vehicle) {
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
}
