package com.tuimm.learningpath.drivers;

import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DriverMapper {
    default FirstName mapFirstName(String value) {
        return FirstName.from(value);
    }
    
    default String mapFirstName(FirstName firstName) {
        return firstName.getValue();
    }
    default LastName mapLastName(String value) {
        return LastName.from(value);
    }

    default String mapFirstName(LastName lastName) {
        return lastName.getValue();
    }

    default Citizenship mapCitizenship(String value) {
        return Citizenship.from(value);
    }

    default String mapCitizenship(Citizenship citizenship) {
        return citizenship.getValue();
    }

    default DrivingLicenseCode mapDrivingLicenseCode(String value) {
        return DrivingLicenseCode.from(value);
    }

    default String mapDrivingLicenseCode(DrivingLicenseCode drivingLicenseCode) {
        return drivingLicenseCode.getValue();
    }
}
