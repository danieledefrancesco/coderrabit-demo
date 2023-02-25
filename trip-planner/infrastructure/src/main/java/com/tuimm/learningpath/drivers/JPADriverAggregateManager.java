package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.common.DomainEventsProcessor;
import com.tuimm.learningpath.drivers.dal.DriversDao;
import com.tuimm.learningpath.exceptions.EntityAlreadyExistsException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class JPADriverAggregateManager implements DriverAggregateManager {
    private final DriversDao dao;
    private final DriverEntityMapper driverEntityMapper;
    @Getter
    private final DomainEventsProcessor domainEventsProcessor;
    @Override
    public void save(Driver driver) {
        if (!dao.existsById(driver.getId()) && driver.getDrivingLicense() != null) {
            dao.findFirstByDrivingLicenseCode(driver.getDrivingLicense().getCode().getValue())
                    .ifPresent(driverEntity -> {
                        throw new EntityAlreadyExistsException("DrivingLicense", driver.getDrivingLicense().getCode().getValue());
                    });
        }
        dao.save(driverEntityMapper.mapToEntity(driver));
    }

    @Override
    public void delete(Driver driver) {
        dao.deleteById(driver.getId());
    }
}
