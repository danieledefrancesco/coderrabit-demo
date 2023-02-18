package com.tuimm.learningpath.drivers.commands;

import com.tuimm.learningpath.drivers.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;
import java.util.function.Consumer;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class CreateDriverCommandTest {
    private DriverFactory driverFactory;
    private DriversRepository driversRepository;
    private CreateDriverCommand createDriverCommand;

    @BeforeEach
    void setUp() {
        driverFactory = mock(DriverFactory.class);
        driversRepository = mock(DriversRepository.class);
        createDriverCommand = new CreateDriverCommand(driversRepository, driverFactory);
    }
    @Test
    void handle_shouldReturnExpectedResult_whenDrivingLicenseIsNotNull() {
        String licenseCode = "licenseCode";
        LocalDate licenseExpiryDate = LocalDate.of(2023,1,2);
        CreateDrivingLicenseRequest createDrivingLicenseRequest = new CreateDrivingLicenseRequest();
        createDrivingLicenseRequest.setCode(licenseCode);
        createDrivingLicenseRequest.setExpiryDate(licenseExpiryDate);

        DrivingLicense drivingLicense = DrivingLicense.builder()
                .expiryDate(licenseExpiryDate)
                .code(DrivingLicenseCode.from(licenseCode))
                .build();

        testHandle(createDrivingLicenseRequest, drivingLicense);
    }

    @Test
    void handle_shouldReturnExpectedResult_whenDrivingLicenseIsNull() {
        testHandle(null, null);
    }

    void testHandle(CreateDrivingLicenseRequest createDrivingLicenseRequest, DrivingLicense drivingLicense) {
        String firstName = "firstName";
        String lastName = "lastName";
        String citizenship = "citizenship";
        LocalDate dateOfBirth = LocalDate.of(2023,1,1);
        CreateDriverRequest createDriverRequest = new CreateDriverRequest();
        createDriverRequest.setCitizenship(citizenship);
        createDriverRequest.setDateOfBirth(dateOfBirth);
        createDriverRequest.setDrivingLicense(createDrivingLicenseRequest);
        createDriverRequest.setFirstName(firstName);
        createDriverRequest.setLastName(lastName);
        Driver driver = mock(Driver.class);


        Driver.DriverBuilder builder = mock(Driver.DriverBuilder.class);
        when(builder.citizenship(Citizenship.from(citizenship))).thenReturn(builder);
        when(builder.firstName(FirstName.from(firstName))).thenReturn(builder);
        when(builder.lastName(LastName.from(lastName))).thenReturn(builder);
        when(builder.dateOfBirth(dateOfBirth)).thenReturn(builder);
        when(builder.drivingLicense(drivingLicense)).thenReturn(builder);

        when(driverFactory.create(any())).then((invocation) -> {
            invocation.getArgument(0, Consumer.class).accept(builder);
            return driver;
        });

        Assertions.assertEquals(driver,
                createDriverCommand.handle(createDriverRequest));

        verify(driversRepository).add(driver);
        verify(driverFactory).create(any());
        verify(builder).citizenship(Citizenship.from(citizenship));
        verify(builder).drivingLicense(drivingLicense);
        verify(builder).firstName(FirstName.from(firstName));
        verify(builder).lastName(LastName.from(lastName));
        verify(builder).dateOfBirth(dateOfBirth);
    }
}
