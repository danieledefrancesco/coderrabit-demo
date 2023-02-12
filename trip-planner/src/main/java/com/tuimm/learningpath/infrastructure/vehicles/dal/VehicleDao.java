package com.tuimm.learningpath.infrastructure.vehicles.dal;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface VehicleDao extends CrudRepository<VehicleEntity, UUID> {
    Iterable<VehicleEntity> findByMaxPeopleGreaterThanEqual(int maxPeople);
}
