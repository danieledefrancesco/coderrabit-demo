package com.tuimm.learningpath.drivers;

import com.tuimm.learningpath.TodayDateProvider;
import com.tuimm.learningpath.drivers.dal.DriverEntity;
import com.tuimm.learningpath.drivers.dal.DrivingLicenseEntity;
import com.tuimm.learningpath.drivers.dal.SlotEntity;
import org.mapstruct.InjectionStrategy;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(uses = { DriverMapper.class, TodayDateProvider.class },  componentModel = "spring", injectionStrategy = InjectionStrategy.FIELD)
public abstract class DriverEntityMapper {
    @Autowired
    protected TodayDateProvider todayDateProvider;
    public abstract DriverEntity mapToEntity(Driver driver);
    @Mapping(target = "todayDateProvider", expression = "java(todayDateProvider)")
    public abstract Driver mapToDriver(DriverEntity entity);
    public abstract DrivingLicenseEntity mapToEntity(DrivingLicense drivingLicense);
    public abstract DrivingLicense mapToDrivingLicense(DrivingLicenseEntity entity);
    public abstract SlotEntity mapToEntity(Slot slot);
    public abstract Slot mapToSlot(SlotEntity slot);
}
