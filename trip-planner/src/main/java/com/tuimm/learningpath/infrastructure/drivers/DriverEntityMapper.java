package com.tuimm.learningpath.infrastructure.drivers;

import com.tuimm.learningpath.domain.drivers.*;
import com.tuimm.learningpath.infrastructure.drivers.dal.DriverEntity;
import com.tuimm.learningpath.infrastructure.drivers.dal.DrivingLicenseEntity;
import org.mapstruct.Mapper;

@Mapper(uses = { DriverMapper.class })
public interface DriverEntityMapper {
    DriverEntity mapToEntity(Driver driver);
    Driver mapToDriver(DriverEntity entity);
    DrivingLicenseEntity mapToEntity(DrivingLicense drivingLicense);
    DrivingLicense mapToDrivingLicense(DrivingLicenseEntity entity);
}
