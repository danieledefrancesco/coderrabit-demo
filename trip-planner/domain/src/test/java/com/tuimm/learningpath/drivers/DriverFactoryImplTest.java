package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.RandomIdGenerator;
import com.tuimm.learningpath.TodayDateProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class DriverFactoryImplTest {
    private RandomIdGenerator randomIdGenerator;
    private DriverFactoryImpl driverFactory;
    private TodayDateProvider todayDateProvider;
    private DriverAggregateManager aggregateManager;

    @BeforeEach
    void setUp() {
        randomIdGenerator = mock(RandomIdGenerator.class);
        todayDateProvider = mock(TodayDateProvider.class);
        aggregateManager = mock(DriverAggregateManager.class);
        driverFactory = new DriverFactoryImpl(randomIdGenerator, todayDateProvider, aggregateManager);
    }

    @Test
    void create_shouldReturnExpectedResult() {
        UUID driverId = UUID.fromString("00000000-0000-0000-0000-000000000001");
        String firstName = "firstName";
        String lastName = "lastName";
        String citizenship = "citizenship";
        String licenseCode = "licenseCode";
        LocalDate dateOfBirth = LocalDate.of(2023,1,1);
        LocalDate licenseExpiryDate = LocalDate.of(2023,1,2);
        DrivingLicense drivingLicense = DrivingLicense.builder()
                .code(DrivingLicenseCode.from(licenseCode))
                .expiryDate(licenseExpiryDate)
                .build();
        when(randomIdGenerator.generateRandomId()).thenReturn(driverId);
        Driver driver = driverFactory.create(driverBuilder -> driverBuilder
                .drivingLicense(drivingLicense)
                .lastName(LastName.from(lastName))
                .firstName(FirstName.from(firstName))
                .dateOfBirth(dateOfBirth)
                .citizenship(Citizenship.from(citizenship)));
        Assertions.assertEquals(driverId, driver.getId());
        Assertions.assertEquals(firstName, driver.getFirstName().getValue());
        Assertions.assertEquals(lastName, driver.getLastName().getValue());
        Assertions.assertEquals(citizenship, driver.getCitizenship().getValue());
        Assertions.assertEquals(dateOfBirth, driver.getDateOfBirth());
        Assertions.assertEquals(licenseCode, driver.getDrivingLicense().getCode().getValue());
        Assertions.assertEquals(licenseExpiryDate, driver.getDrivingLicense().getExpiryDate());
    }
}
