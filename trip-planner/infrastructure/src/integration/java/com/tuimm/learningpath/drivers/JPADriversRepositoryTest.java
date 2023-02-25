package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.IntegrationTest;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Collections;
import java.util.UUID;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@IntegrationTest
class JPADriversRepositoryTest {
    private static final UUID ID = UUID.fromString("00000000-0000-0000-0000-000000000001");
    private static final String FIRST_NAME = "firstName";
    private static final String LAST_NAME = "lastName";
    private static final String CITIZENSHIP = "citizenship";
    private static final LocalDate DATE_OF_BIRTH = LocalDate.parse("2023-01-01");
    private static final String CODE = "code";
    private static final LocalDate EXPIRY_DATE = LocalDate.parse("2023-12-12");
    private JPADriversRepository repository;
    @Autowired
    private DriversDao dao;
    @Autowired
    private DriverEntityMapper mapper;
    @Mock
    private TodayDateProvider todayDateProvider;

    @BeforeEach
    void setUp() {
        repository = new JPADriversRepository(dao, mapper, todayDateProvider);
        dao.deleteAll();
    }

    @Test
    void findById_returnsExpectedDriver_whenDriverExists() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        Driver driver = repository.findById(ID);
        assertIsExpectedDriver(driver);
    }

    @Test
    void findById_throwsEntityNotFoundException_whenDriverDoesNotExist() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        UUID secondId = UUID.fromString("00000000-0000-0000-0000-000000000002");
        EntityNotFoundException actualException = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> repository.findById(secondId));
        Assertions.assertEquals("Driver with id 00000000-0000-0000-0000-000000000002 does not exist.",
                actualException.getMessage());
    }

    @Test
    void findByMinimumAgeAndValidLicense_shouldReturnExpectedDriver_whenDriverHasExactlyRequiredAgeAndValidLicense() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.findByMinimumAgeAndValidLicense(minimumAge, EXPIRY_DATE.minusDays(1));
        Assertions.assertEquals(1, drivers.size());
        assertIsExpectedDriver(drivers.stream().findFirst().orElseThrow());
    }

    @Test
    void findByMinimumAgeAndValidLicense_shouldReturnExpectedDriver_whenDriverHasMoreThanRequiredAgeAndValidLicense() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge).plusDays(1);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.findByMinimumAgeAndValidLicense(minimumAge, EXPIRY_DATE.minusDays(1));
        Assertions.assertEquals(1, drivers.size());
        assertIsExpectedDriver(drivers.stream().findFirst().orElseThrow());
    }

    @Test
    void findByMinimumAgeAndValidLicense_shouldReturnEmptyCollection_whenDriverHasLessThanRequiredAgeAndValidLicense() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge).plusDays(1);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.findByMinimumAgeAndValidLicense(minimumAge, EXPIRY_DATE.plusDays(1));
        Assertions.assertEquals(0, drivers.size());
    }

    @Test
    void findByMinimumAgeAndValidLicense_shouldReturnEmptyCollection_whenDriverHasMoreThanRequiredAgeAndNoValidLicense() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge).minusDays(1);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.findByMinimumAgeAndValidLicense(minimumAge, EXPIRY_DATE.minusDays(1));
        Assertions.assertEquals(0, drivers.size());
    }

    @Test
    void findAll_shouldReturnAllTheDrivers() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        Collection<Driver> drivers = repository.findAll();
        Assertions.assertEquals(1, drivers.size());
    }

    private void assertIsExpectedDriver(Driver driver) {
        Assertions.assertEquals(ID, driver.getId());
        Assertions.assertEquals(FIRST_NAME, driver.getFirstName().getValue());
        Assertions.assertEquals(LAST_NAME, driver.getLastName().getValue());
        Assertions.assertEquals(CITIZENSHIP, driver.getCitizenship().getValue());
        Assertions.assertEquals(DATE_OF_BIRTH, driver.getDateOfBirth());
        Assertions.assertEquals(CODE, driver.getDrivingLicense().getCode().getValue());
        Assertions.assertEquals(EXPIRY_DATE, driver.getDrivingLicense().getExpiryDate());
    }

    private static DriverEntity createDriverEntity() {
        DrivingLicenseEntity drivingLicenseEntity = new DrivingLicenseEntity();
        drivingLicenseEntity.setExpiryDate(EXPIRY_DATE);
        drivingLicenseEntity.setCode(CODE);
        DriverEntity driverEntity = new DriverEntity();
        driverEntity.setCitizenship(CITIZENSHIP);
        driverEntity.setDateOfBirth(DATE_OF_BIRTH);
        driverEntity.setFirstName(FIRST_NAME);
        driverEntity.setLastName(LAST_NAME);
        driverEntity.setId(ID);
        driverEntity.setDrivingLicense(drivingLicenseEntity);
        return driverEntity;
    }

    private static Driver createDriver(UUID id, DrivingLicense license) {
        return Driver.builder()
                .dateOfBirth(DATE_OF_BIRTH)
                .id(id)
                .lastName(LastName.from(LAST_NAME))
                .firstName(FirstName.from(FIRST_NAME))
                .citizenship(Citizenship.from(CITIZENSHIP))
                .drivingLicense(license)
                .todayDateProvider(mock(TodayDateProvider.class))
                .reservedTimeSlots(Collections.emptyList())
                .build();
    }
}
