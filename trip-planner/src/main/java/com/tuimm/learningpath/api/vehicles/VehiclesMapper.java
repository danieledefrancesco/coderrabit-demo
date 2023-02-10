package com.tuimm.learningpath.api.vehicles;

import com.tuimm.learningpath.contracts.vehicles.BikeResponse;
import com.tuimm.learningpath.contracts.vehicles.VehicleResponse;
import com.tuimm.learningpath.domain.vehicles.Bike;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import org.mapstruct.Mapper;

@Mapper
public interface VehiclesMapper {
    BikeResponse mapBike(Bike bike);
    default VehicleResponse mapVehicle(Vehicle vehicle) {
        if (vehicle instanceof Bike bike) {
            return mapBike(bike);
        }
        return null;
    }
}
