package com.tuimm.learningpath.infrastructure.drivers.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface DriversDao extends CrudRepository<DriverEntity, UUID> {
    Iterable<DriverEntity> getByDateOfBirthBefore(LocalDate date);
}
