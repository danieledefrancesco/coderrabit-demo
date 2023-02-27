package com.tuimm.learningpath.drivers.dal;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface DriversDao extends CrudRepository<DriverEntity, UUID> {
    Optional<DriverEntity> findFirstByDrivingLicenseCode(String code);
}
