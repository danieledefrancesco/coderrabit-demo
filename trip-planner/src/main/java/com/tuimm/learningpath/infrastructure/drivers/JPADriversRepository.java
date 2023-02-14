package com.tuimm.learningpath.infrastructure.drivers;

import com.googlecode.concurrenttrees.common.Iterables;
import com.tuimm.learningpath.common.TodayDateProvider;
import com.tuimm.learningpath.domain.drivers.Driver;
import com.tuimm.learningpath.domain.drivers.DriversRepository;
import com.tuimm.learningpath.domain.exceptions.EntityNotFoundException;
import com.tuimm.learningpath.infrastructure.drivers.dal.DriverEntity;
import com.tuimm.learningpath.infrastructure.drivers.dal.DriversDao;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Collection;
import java.util.UUID;

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
        return Iterables.toList(driverEntities)
                .stream()
                .map(driverEntityMapper::mapToDriver)
                .toList();
    }

    @Override
    public Driver getById(UUID id) {
        DriverEntity driverEntity = dao.findById(id).orElseThrow(() -> new EntityNotFoundException("Driver", id.toString()));
        return driverEntityMapper.mapToDriver(driverEntity);
    }
}
