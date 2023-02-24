package com.tuimm.learningpath.vehicles;

import com.tuimm.learningpath.common.DomainEventsProcessor;
import com.tuimm.learningpath.vehicles.dal.VehiclesDao;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class JPAVehicleAggregateManager implements VehicleAggregateManager {
    private final VehiclesDao dao;
    private final VehicleEntitiesMapper mapper;
    @Getter
    private final DomainEventsProcessor domainEventsProcessor;

    @Override
    public void save(Vehicle vehicle) {
        dao.save(mapper.mapToEntity(vehicle));
    }

    @Override
    public void delete(Vehicle vehicle) {
        dao.deleteById(vehicle.getId());
    }
}
