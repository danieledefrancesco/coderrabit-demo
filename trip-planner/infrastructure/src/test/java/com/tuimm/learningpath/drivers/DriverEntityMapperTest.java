package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import java.time.LocalDate;
import java.util.UUID;

class DriverEntityMapperTest {
    DriverEntityMapper driverEntityMapper = Mappers.getMapper(DriverEntityMapper.class);

    @Test
    void mapToEntity_shouldReturnExpectedEntity_whenDriverIsNotNull() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String firstName = "firstName";
        String lastName = "lastName";
        LocalDate dateOfBirth = LocalDate.parse("2023-01-01");
        String citizenship = "citizenship";
        String code = "code";
        LocalDate expiryDate = LocalDate.parse("2023-01-02");
        
        DrivingLicense license = DrivingLicense.builder()
                .code(DrivingLicenseCode.from(code))
                .expiryDate(expiryDate)
                .build();
        Driver driver = Driver.builder()
                .id(id)
                .citizenship(Citizenship.from(citizenship))
                .dateOfBirth(dateOfBirth)
                .drivingLicense(license)
                .firstName(FirstName.from(firstName))
                .lastName(LastName.from(lastName))
                .build();
        
        DriverEntity driverEntity = driverEntityMapper.mapToEntity(driver);
        DrivingLicenseEntity drivingLicenseEntity = driverEntity.getDrivingLicense();

        Assertions.assertEquals(id, driverEntity.getId());
        Assertions.assertEquals(citizenship, driverEntity.getCitizenship());
        Assertions.assertEquals(dateOfBirth, driverEntity.getDateOfBirth());
        Assertions.assertEquals(firstName, driverEntity.getFirstName());
        Assertions.assertEquals(lastName, driverEntity.getLastName());
        Assertions.assertEquals(code, drivingLicenseEntity.getCode());
        Assertions.assertEquals(expiryDate, drivingLicenseEntity.getExpiryDate());
    }

    @Test
    void mapToEntity_shouldReturnNull_whenDriverIsNull() {
        Assertions.assertNull(driverEntityMapper.mapToEntity((Driver)null));
    }

    @Test
    void mapToEntity_shouldReturnNull_whenDrivingLicenseIsNull() {
        Assertions.assertNull(driverEntityMapper.mapToEntity((DrivingLicense) null));
    }

    @Test
    void mapToDriver_shouldReturnExpectedDriver_whenEntityIsNotNull() {
        UUID id = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String firstName = "firstName";
        String lastName = "lastName";
        LocalDate dateOfBirth = LocalDate.parse("2023-01-01");
        String citizenship = "citizenship";
        String code = "code";
        LocalDate expiryDate = LocalDate.parse("2023-01-02");
        
        DriverEntity driverEntity = new DriverEntity();
        DrivingLicenseEntity drivingLicenseEntity = new DrivingLicenseEntity();
        driverEntity.setId(id);
        driverEntity.setFirstName(firstName);
        driverEntity.setLastName(lastName);
        driverEntity.setDrivingLicense(drivingLicenseEntity);
        driverEntity.setDateOfBirth(dateOfBirth);
        driverEntity.setCitizenship(citizenship);
        drivingLicenseEntity.setCode(code);
        drivingLicenseEntity.setExpiryDate(expiryDate);
        
        Driver driver = driverEntityMapper.mapToDriver(driverEntity);
        DrivingLicense drivingLicense = driver.getDrivingLicense();

        Assertions.assertEquals(id, driver.getId());
        Assertions.assertEquals(citizenship, driver.getCitizenship().getValue());
        Assertions.assertEquals(dateOfBirth, driver.getDateOfBirth());
        Assertions.assertEquals(firstName, driver.getFirstName().getValue());
        Assertions.assertEquals(lastName, driver.getLastName().getValue());
        Assertions.assertEquals(code, drivingLicense.getCode().getValue());
        Assertions.assertEquals(expiryDate, drivingLicenseEntity.getExpiryDate());
    }

    @Test
    void mapToDriver_shouldReturnNull_whenEntityIsNull() {
        Assertions.assertNull(driverEntityMapper.mapToDriver(null));
    }

    @Test
    void mapToDrivingLicense_shouldReturnNull_whenEntityIsNull() {
        Assertions.assertNull(driverEntityMapper.mapToDrivingLicense(null));
    }
}
