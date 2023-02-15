package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import org.mapstruct.Mapper;

@Mapper(uses = { DriverMapper.class })
public interface DriverEntityMapper {
    DriverEntity mapToEntity(Driver driver);
    Driver mapToDriver(DriverEntity entity);
    DrivingLicenseEntity mapToEntity(DrivingLicense drivingLicense);
    DrivingLicense mapToDrivingLicense(DrivingLicenseEntity entity);
}
