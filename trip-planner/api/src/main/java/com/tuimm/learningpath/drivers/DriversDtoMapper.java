package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.commands.CreateDriverRequest;
import com.tuimm.learningpath.drivers.commands.CreateDrivingLicenseRequest;
import com.tuimm.learningpath.drivers.commands.UpdateDriverRequest;
import com.tuimm.learningpath.drivers.dtos.*;
import com.tuimm.learningpath.drivers.queries.GetAllDriversResponse;
import org.mapstruct.Mapper;

import java.util.Optional;
import java.util.UUID;

@Mapper(uses = { DriverMapper.class })
public interface DriversDtoMapper {
    CreateDrivingLicenseRequest map(CreateDrivingLicenseRequestDto request);
    CreateDriverRequest map(CreateDriverRequestDto request);
    DrivingLicenseDto map(DrivingLicense drivingLicense);
    DriverResponseDto map(Driver driver);
    GetAllDriversResponseDto map(GetAllDriversResponse response);
    UpdateDriverRequest map(UpdateDriverRequestDto request, UUID driverId);
    default <T> Optional<T> map(T value) {
        return Optional.ofNullable(value);
    }

}
