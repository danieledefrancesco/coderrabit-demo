package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Repository
@RequiredArgsConstructor
public class JPADriversRepository implements DriversRepository {
    private final DriversDao dao;
    private final DriverEntityMapper driverEntityMapper;
    private final TodayDateProvider todayDateProvider;

    @Override
    public Collection<Driver> findAll() {
        return StreamSupport.stream(dao.findAll().spliterator(), false)
                .map(driverEntityMapper::mapToDriver)
                .toList();
    }

    @Override
    public Collection<Driver> findByMinimumAgeAndValidLicense(int minimumAge, LocalDate tripEndDate) {
        LocalDate maxBirthdayDate = todayDateProvider.getTodayDate().minusYears(minimumAge).plusDays(1);
        Iterable<DriverEntity> driverEntities = dao.getByDateOfBirthBeforeAndDrivingLicenseExpiryDateAfter(maxBirthdayDate, tripEndDate);
        return StreamSupport.stream(driverEntities.spliterator(), false)
                .map(driverEntityMapper::mapToDriver)
                .toList();
    }

    @Override
    public Driver findById(UUID id) {
        return driverEntityMapper.mapToDriver(getDriverOrThrowNotFoundException(id));
    }

    private DriverEntity getDriverOrThrowNotFoundException(UUID id) {
        return dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver", id.toString()));
    }
}
