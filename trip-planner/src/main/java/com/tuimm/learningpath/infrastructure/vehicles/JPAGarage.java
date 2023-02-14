package com.tuimm.learningpath.infrastructure.vehicles;

import com.tuimm.learningpath.domain.vehicles.Garage;
import com.tuimm.learningpath.domain.vehicles.Vehicle;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehiclesDao;
import com.tuimm.learningpath.infrastructure.vehicles.dal.VehicleEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class JPAGarage implements Garage {
    private final VehiclesDao vehiclesDao;
    private final VehicleEntitiesMapper vehiclesMapper;

    @Override
    public Collection<Vehicle> getAllVehicles() {
        Iterable<VehicleEntity> entities = vehiclesDao.findAll();
        return mapToVehicles(entities);
    }

    @Override
    public Collection<Vehicle> getSuitableVehicles(int numberOfPeople) {
        Iterable<VehicleEntity> entities = vehiclesDao.findByMaxPeopleGreaterThanEqual(numberOfPeople);
        return mapToVehicles(entities);
    }

    @Override
    public void addVehicle(Vehicle vehicle) {
        vehiclesDao.save(vehiclesMapper.mapToEntity(vehicle));
    }

    @Override
    public void delete(UUID id) {
        vehiclesDao.deleteById(id);
    }

    private List<Vehicle> mapToVehicles(Iterable<VehicleEntity> entities) {
        List<Vehicle> result = new LinkedList<>();
        for (VehicleEntity entity : entities) {
            result.add(vehiclesMapper.mapToVehicle(entity));
        }
        return result;
    }
}
