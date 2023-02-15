package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface VehicleEntitiesMapper {
    Bike mapToBike(VehicleEntity vehicle);

    Car mapToCar(VehicleEntity vehicle);

    Scooter mapToScooter(VehicleEntity vehicle);

    Pullman mapToPullman(VehicleEntity vehicle);

    default Vehicle mapToVehicle(VehicleEntity vehicle) {
        switch (vehicle.getType()) {
            case CAR -> {
                return mapToCar(vehicle);
            }
            case BIKE -> {
                return mapToBike(vehicle);
            }
            case PULLMAN -> {
                return mapToPullman(vehicle);
            }
            default -> {
                return mapToScooter(vehicle);
            }
        }
    }
    @Mapping(target = "type", constant = "BIKE")

    VehicleEntity mapToEntity(Bike bike);
    @Mapping(target = "type", constant = "CAR")

    VehicleEntity mapToEntity(Car car);
    @Mapping(target = "type", constant = "PULLMAN")

    VehicleEntity mapToEntity(Pullman pullman);

    @Mapping(target = "type", constant = "SCOOTER")
    VehicleEntity mapToEntity(Scooter scooter);

    default VehicleEntity mapToEntity(Vehicle vehicle) {
        if (vehicle instanceof Bike bike) {
            return mapToEntity(bike);
        }
        if (vehicle instanceof Car car) {
            return mapToEntity(car);
        }
        if (vehicle instanceof Pullman pullman) {
            return mapToEntity(pullman);
        }
        if (vehicle instanceof Scooter scooter) {
            return mapToEntity(scooter);
        }
        throw new UnsupportedOperationException();
    }
    default String mapToString(Plate plate) { return plate.getValue(); }
    default GenericPlate mapToGenericPlate(String plate) { return GenericPlate.from(plate); }
    default ScooterPlate mapToScooterPlate(String plate) { return ScooterPlate.from(plate); }
}
