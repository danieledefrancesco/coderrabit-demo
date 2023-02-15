package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.exceptions.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class JPADriversRepository implements DriversRepository {
    private final DriversDao dao;
    private final DriverEntityMapper driverEntityMapper;
    private final TodayDateProvider todayDateProvider;
    @Override
    public Collection<Driver> getByMinimumAge(int minimumAge) {
        LocalDate maxBirthdayDate = todayDateProvider.getTodayDate().minusYears(minimumAge).plusDays(1);
        Iterable<DriverEntity> driverEntities = dao.getByDateOfBirthBefore(maxBirthdayDate);
        return StreamSupport.stream(driverEntities.spliterator(), false)
                .map(driverEntityMapper::mapToDriver)
                .toList();
    }
    @Override
    public Driver getById(UUID id) {
        DriverEntity driverEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver", id.toString()));
        return driverEntityMapper.mapToDriver(driverEntity);
    }

    @Override
    public void add(Driver driver) {
        dao.save(driverEntityMapper.mapToEntity(driver));
    }

    @Override
    public void delete(UUID id) {
        dao.deleteById(id);
    }
}
