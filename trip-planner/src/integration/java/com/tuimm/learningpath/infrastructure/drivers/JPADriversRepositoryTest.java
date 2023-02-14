package com.tuimm.learningpath.infrastructure.drivers;

import com.tuimm.learningpath.common.TodayDateProvider;
import com.tuimm.learningpath.domain.drivers.Driver;
import com.tuimm.learningpath.domain.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.infrastructure.IntegrationTest;
import com.tuimm.learningpath.infrastructure.drivers.dal.DriverEntity;
import com.tuimm.learningpath.infrastructure.drivers.dal.DriversDao;
import com.tuimm.learningpath.infrastructure.drivers.dal.DrivingLicenseEntity;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

import static org.mockito.Mockito.when;

class JPADriversRepositoryTest extends IntegrationTest {
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
    void getById_returnsExpectedDriver_whenDriverExists() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        Driver driver = repository.getById(ID);
        assertIsExpectedDriver(driver);
    }

    @Test
    void getById_throwsEntityNotFoundException_whenDriverDoesNotExist() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        UUID secondId = UUID.fromString("00000000-0000-0000-0000-000000000002");
        EntityNotFoundException actualException = Assertions.assertThrows(
                EntityNotFoundException.class,
                () -> repository.getById(secondId));
        Assertions.assertEquals("Driver with id 00000000-0000-0000-0000-000000000002 does not exist.",
                actualException.getMessage());
    }

    @Test
    void getByMinimumAge_shouldReturnExpectedDriver_whenDriverHasExactlyRequiredAge() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.getByMinimumAge(minimumAge);
        Assertions.assertEquals(1, drivers.size());
        assertIsExpectedDriver(drivers.stream().findFirst().orElseThrow());
    }

    @Test
    void getByMinimumAge_shouldReturnExpectedDriver_whenDriverHasMoreThanRequiredAge() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge).plusDays(1);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.getByMinimumAge(minimumAge);
        Assertions.assertEquals(1, drivers.size());
        assertIsExpectedDriver(drivers.stream().findFirst().orElseThrow());
    }

    @Test
    void getByMinimumAge_shouldReturnEmptyCollection_whenDriverHasLessThanRequiredAge() {
        DriverEntity driverEntity = createDriverEntity();
        dao.save(driverEntity);
        int minimumAge = 18;
        LocalDate todayDate = DATE_OF_BIRTH.plusYears(minimumAge).minusDays(1);
        when(todayDateProvider.getTodayDate()).thenReturn(todayDate);
        Collection<Driver> drivers = repository.getByMinimumAge(minimumAge);
        Assertions.assertEquals(0, drivers.size());
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
}
