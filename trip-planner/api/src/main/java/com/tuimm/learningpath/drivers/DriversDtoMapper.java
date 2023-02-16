package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.commands.CreateDriverRequest;
import com.tuimm.learningpath.drivers.commands.CreateDrivingLicenseRequest;
import com.tuimm.learningpath.drivers.dtos.*;
import com.tuimm.learningpath.drivers.queries.GetAllDriversResponse;
import org.mapstruct.Mapper;

@Mapper(uses = { DriverMapper.class })
public interface DriversDtoMapper {
    CreateDrivingLicenseRequest map(CreateDrivingLicenseRequestDto request);
    CreateDriverRequest map(CreateDriverRequestDto request);
    DrivingLicenseDto map(DrivingLicense drivingLicense);
    DriverDto map(Driver driver);
    GetAllDriversResponseDto map(GetAllDriversResponse response);
}
