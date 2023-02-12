package com.tuimm.learningpath.infrastructure.vehicles;

import com.tuimm.learningpath.domain.vehicles.Garage;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleDao;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JPAGarage implements Garage {
    private final VehicleDao vehicleDao;
    private final VehicleEntitiesMapper vehiclesMapper;

    @Override
    public Collection<Vehicle> getAllVehicles() {
        Iterable<VehicleEntity> entities = vehicleDao.findAll();
        return mapToVehicles(entities);
    }

    @Override
    public Collection<Vehicle> getSuitableVehicles(int numberOfPeople) {
        Iterable<VehicleEntity> entities = vehicleDao.findByMaxPeopleGreaterThanEqual(numberOfPeople);
        return mapToVehicles(entities);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehicleDao.save(vehiclesMapper.mapToEntity(vehicle));
    }

    @Override
    public void delete(UUID id) {
        vehicleDao.deleteById(id);
    }

    private List<Vehicle> mapToVehicles(Iterable<VehicleEntity> entities) {
        List<Vehicle> result = new LinkedList<>();
        for (VehicleEntity entity : entities) {
            result.add(vehiclesMapper.mapToVehicle(entity));
        }
        return result;
    }
}
