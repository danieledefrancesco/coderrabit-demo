package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.vehicles.dal.VehicleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;

@Mapper(componentModel = "spring")
public abstract class VehicleEntitiesMapper {
    @Lazy
    @Autowired
    protected VehicleAggregateManager aggregateManager;

    @Mapping(target = "aggregateManager", expression = "java(aggregateManager)")
    public abstract Bike mapToBike(VehicleEntity vehicle);

    @Mapping(target = "aggregateManager", expression = "java(aggregateManager)")
    public abstract Car mapToCar(VehicleEntity vehicle);

    @Mapping(target = "aggregateManager", expression = "java(aggregateManager)")
    public abstract Scooter mapToScooter(VehicleEntity vehicle);

    @Mapping(target = "aggregateManager", expression = "java(aggregateManager)")
    public abstract Pullman mapToPullman(VehicleEntity vehicle);

    @Named("mapToVehicle")
    public Vehicle mapToVehicle(VehicleEntity vehicle) {
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
    public abstract VehicleEntity mapToEntity(Bike bike);

    @Mapping(target = "type", constant = "CAR")
    public abstract VehicleEntity mapToEntity(Car car);

    @Mapping(target = "type", constant = "PULLMAN")
    public abstract VehicleEntity mapToEntity(Pullman pullman);

    @Mapping(target = "type", constant = "SCOOTER")
    public abstract VehicleEntity mapToEntity(Scooter scooter);

    public VehicleEntity mapToEntity(Vehicle vehicle) {
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

    public String mapToString(Plate plate) {
        return plate.getValue();
    }

    public GenericPlate mapToGenericPlate(String plate) {
        return GenericPlate.from(plate);
    }

    public ScooterPlate mapToScooterPlate(String plate) {
        return ScooterPlate.from(plate);
    }
}
